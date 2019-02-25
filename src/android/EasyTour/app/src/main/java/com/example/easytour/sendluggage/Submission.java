package com.example.easytour.sendluggage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.createluggage.NewTravel;
import com.example.easytour.createluggage.TimeBean;
import com.example.easytour.sendluggage.delivery.Delivery;
import com.example.easytour.sendluggage.delivery.DeliveryBean;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Submission extends AppCompatActivity {
    private String now_city;
    private String now_address;
    private String after_address;
    private TextView tv_send_address;   //寄出地点
    private TextView tv_now_address;    //出发地
    private TextView tv_daohang;    //导航
    private TextView tv_after_address;  //目的地点
    private Button btn_Add; //+
    private Button btn_Sub; //-
    private TextView tv_num;  //件数
    private TextView tv_submit; //提交
    private TextView tv_money;
    private TextView tv_send;
    private TextView tv_get;
    private ArrayList<TimeBean> toptions1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> toptions2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> toptions3Items = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    int num = 1;
    int money = 15;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        now_city = getIntent().getStringExtra("now_city");
        now_address = getIntent().getStringExtra("now_address");
        after_address = getIntent().getStringExtra("after_address");
        userInfo = (UserInfo)getIntent().getSerializableExtra("userinfo");

        tv_send_address = (TextView)findViewById(R.id.send_address);   //寄出地点
        tv_now_address = (TextView)findViewById(R.id.now_address);    //出发地
        tv_daohang = (TextView)findViewById(R.id._daohang);    //导航
        tv_after_address = (TextView)findViewById(R.id.after_address);  //目的地点
        btn_Add = (Button)findViewById(R.id._btnAdd); //+
        btn_Sub = (Button)findViewById(R.id._btnSub); //-
        tv_num = (TextView)findViewById(R.id._tvNum);  //件数
        tv_submit = (TextView)findViewById(R.id.submit); //提交
        tv_money = (TextView)findViewById(R.id.tvAllmoney);
        tv_send = (TextView)findViewById(R.id.sendtime);
        tv_get = (TextView)findViewById(R.id.gettime);

        sendRequestWithOkHttpt();
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerViewt(tv_send,0);
            }
        });
        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerViewt(tv_get,1);
            }
        });

        tv_send_address.setText(now_city);
        tv_now_address.setText(now_address);
        tv_after_address.setText(after_address);
        tv_money.setText(Integer.toString(money*num));
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_num.setText(Integer.toString(Integer.parseInt(tv_num.getText().toString())+1));
                num++;
                tv_money.setText(Integer.toString(money*num));
            }
        });
        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tv_num.getText().toString())!=0)
                tv_num.setText(Integer.toString(Integer.parseInt(tv_num.getText().toString())-1));
                num--;
                tv_money.setText(Integer.toString(money*num));
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliveryBean deliveryBean = new DeliveryBean();
                deliveryBean.Add(userInfo.getUserid().toString(),now_city,now_address,after_address,num,tv_money.getText().toString());
                Intent intent = new Intent(Submission.this, Delivery.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);

            }
        });
    }

    private void showPickerViewt(final TextView tv_time,final int flag){
        //监听选中
        final OptionsPickerView pickerView = new OptionsPickerBuilder(Submission.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别选中位置
                String tx = toptions1Items.get(options1).getPickerViewText()+"--"+
                        toptions2Items.get(options1).get(options2)+"--"+
                        toptions3Items.get(options1).get(options2).get(options3);//要展示的数据
                tv_time.setText(tx);//展示结果
            }
        })
                .setTitleText("时间选择")
                .setContentTextSize(20) //滚轮文字大小
                .setDividerColor(Color.LTGRAY)  //分割线颜色
                .setSelectOptions(0,0,0)    //默认选择项
                .setTitleBgColor(Color.LTGRAY)
                .setOutSideCancelable(false)
                .build();
        //数据绑定到控件上
        //pickerView.setPicker(listData);
        pickerView.setPicker(toptions1Items,toptions2Items,toptions3Items);
        pickerView.show();
    }

    /**
     * 获取网络上的JSON格式数据
     * 获取数据：省市区 province.json
     */
    private void sendRequestWithOkHttpt(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/EasytourServer/Time")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    Log.d("NewTravel",responseData);
                    initJsonDatat(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initJsonDatat(String responseData){
        ArrayList<TimeBean> timeBean = parseJSONWithGSONt(responseData);//用GSON转换成实例
        toptions1Items = timeBean;

        StringBuilder builder = new StringBuilder();
        for (int i=0;i<timeBean.size();i++){
            ArrayList<String> Monthlist = new ArrayList<>();    //月份列表
            ArrayList<ArrayList<String>> Time_dayList = new ArrayList<>();  //日期列表
            for(int j=0;j<timeBean.get(i).getMonth().size();j++){   //遍历所有月份
                String Monthname = timeBean.get(i).getMonth().get(j).getMonth();
                Monthlist.add(Monthname);
                ArrayList<String> Month_dayList = new ArrayList<>();//日期
                if(timeBean.get(i).getMonth().get(j).getDay() == null || timeBean.get(i).getMonth().get(j).getDay().size() == 0){
                    Month_dayList.add("");
                }else {
                    Month_dayList.addAll(timeBean.get(i).getMonth().get(j).getDay());
                    for(int k = 0;k<timeBean.get(i).getMonth().get(j).getDay().size();k++) {
                        builder.append(timeBean.get(i).getYear()).append("--")
                                .append(Monthname).append("--")
                                .append(timeBean.get(i).getMonth().get(j).getDay().get(k)).append("--")
                                .append("\n");
                    }
                }
                Time_dayList.add(Month_dayList);
            }
            toptions2Items.add(Monthlist);
            toptions3Items.add(Time_dayList);
        }
    }

    private ArrayList<TimeBean> parseJSONWithGSONt(String jsonData){
        ArrayList<TimeBean> detail =new ArrayList<>();
        try {
            JSONArray data = new JSONArray(jsonData);
            Gson gson = new Gson();
            for (int i=0;i<data.length();i++){
                TimeBean entity = gson.fromJson(data.optJSONObject(i).toString(),TimeBean.class);
                detail.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.d("sd",jsonData);

        Log.d("sd",detail.toString());
        return detail;
    }


    public void alert_edit(final TextView time){
        //final DataBean bean = new DataBean(dbHelper);
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("请输入信息")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        time.setText(et.getText().toString());
                        Toast.makeText(getApplicationContext(), time.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("取消",null).show();
    }
}
