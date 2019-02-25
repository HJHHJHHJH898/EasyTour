package com.example.easytour.createluggage;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dd.CircularProgressButton;
import com.example.easytour.MainActivity;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.createluggage.selectyourluggage.SelectLuggages;
import com.example.easytour.createluggage.CalendarUtil.customview.CustomCalendarView;
import com.example.easytour.createluggage.CalendarUtil.util.inject.Inject;
import com.example.easytour.createluggage.CalendarUtil.util.inject.ViewInject;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.test.CommentStudy;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewTravel extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    UserInfo userInfo;
    private ArrayList<PlaceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<TimeBean> toptions1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> toptions2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> toptions3Items = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    boolean isOK = false;

    Button _tvdest;   //目的地
    TextView _tvtraveltime;//alltime
    TextView _tvdaynight;//dayandnight
    Button _tvOK;//OK
    Toolbar toolbarback;
    @Inject(R.id.calendarview)
    private CustomCalendarView calendarView;
    private List<String> initeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);
        //设置菜单页面
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.all);
        }
        navigationView.setCheckedItem(R.id.nav_allluggage);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_allluggage:
                        Intent intent = new Intent(NewTravel.this,Allluggages.class);
                        intent.putExtra("userinfo",userInfo);
                        startActivity(intent);
                        break;
                    case R.id.nav_send:
                        Intent intentsend = new Intent(NewTravel.this,luggage.class);
                        intentsend.putExtra("userinfo",userInfo);
                        startActivity(intentsend);
                        break;
                    case R.id.person:
                        Intent intentperson = new Intent(NewTravel.this,Personallnf.class);
                        intentperson.putExtra("userinfo",userInfo);
                        startActivity(intentperson);
                        break;
                    case R.id.nav_tip:
                        Intent intenttip = new Intent(NewTravel.this,CommentStudy.class);
                        intenttip.putExtra("userinfo",userInfo);
                        startActivity(intenttip);
                        break;
                    default:
                }
                return true;
            }
        });

        userInfo = (UserInfo) getIntent().getSerializableExtra("userinfo");

        ViewInject.inject(this);
        initeData=new ArrayList<>();
        calendarView.setSelect(initeData);

        final CircularProgressButton circularButton03 = (CircularProgressButton) findViewById(R.id.btn_OK);
        circularButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton03.getProgress() == 0) {
                    simulateSuccessProgress(circularButton03); // 如果是初始状态就开始进入进度条动画
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (circularButton03.getProgress()!=100);
                            Intent intent = new Intent(NewTravel.this, SelectLuggages.class);
                            String Address = _tvdest.getText().toString();
                            String str[] = new Gson().toJson(calendarView.getSelectdateList()).split(",");
                            String str1[] = str[0].split("\"");
                            String str2[] = str[1].split("\"");
                            String startDay = str1[1];
                            String endDay = str2[1];
                            intent.putExtra("start",startDay);
                            intent.putExtra("end",endDay);
                            intent.putExtra("dest",Address);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }
                    }).start();
                } else {
                    circularButton03.setProgress(0); // 如果不是初始状态，那么就回到初始状态
                }
            }
        });


        sendRequestWithOkHttp();

        _tvdest = (Button)findViewById(R.id.btn_dest);   //目的地
        _tvtraveltime = (TextView)findViewById(R.id.tv_traveltime);//alltime
        _tvdaynight = (TextView)findViewById(R.id.tv_dayandnight);//dayandnight
        toolbarback = (Toolbar)findViewById(R.id.toolbar);

        toolbarback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTravel.this, MainActivity.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);
            }
        });

        //目的地选择
        _tvdest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();//弹出选择器
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbarper,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(getApplicationContext(),"Backup",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewTravel.this, MainActivity.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    /**
     * 设置成功的进度
     * @param button
     */
    private void simulateSuccessProgress(final CircularProgressButton button) {
        // 这里巧妙运用了valueAnimator这个类来计算动画的值，这个类本身就起计算作用，不处理任何动画，这里在计算好后自行进行了进度的设定
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100); // 设定范围为1到100
        widthAnimation.setDuration(1500); // 设定动画的持续时间
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator()); // 设定动画的插值器
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // 在动画进行时进行处理
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value); // 设置进度为当前动画的进度
            }
        });
        widthAnimation.start(); // 开始动画的计算工作
    }

    /**
     * 展示选择器
     * 第三方库
     * 参考：https://github.com/Bigkoo/Android-PickerView
     */
    private void showPickerView(){
        //监听选中
        final OptionsPickerView pickerView = new OptionsPickerBuilder(NewTravel.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);//要展示的数据
                _tvdest.setText(tx);//展示结果
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20) //滚轮文字大小
                .setDividerColor(Color.LTGRAY)  //分割线颜色
                .setSelectOptions(0,0,0)    //默认选择项
                .setTitleBgColor(Color.LTGRAY)
                //.setLabels("省","市","区") //三级选择器
                .setOutSideCancelable(false)
                .build();
        //数据绑定到控件上
        //pickerView.setPicker(listData);
        //Log.d("NewTravel", "111");
        pickerView.setPicker(options1Items,options2Items,options3Items);
        pickerView.show();
    }

    private void showPickerViewt(final Button btn_time,final int flag){
        //监听选中
        final OptionsPickerView pickerView = new OptionsPickerBuilder(NewTravel.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别选中位置
                String tx = toptions1Items.get(options1).getPickerViewText()+"--"+
                        toptions2Items.get(options1).get(options2)+"--"+
                        toptions3Items.get(options1).get(options2).get(options3);//要展示的数据
                btn_time.setText(tx);//展示结果
                if (flag ==0){
                    builder.append(tx+",");
                }
                if(flag == 1){
                    int year;
                    int month;
                    int day;
                    builder.append(tx);
                    String str[] = builder.toString().split(",");

                    String start[] = str[0].split("--");
                    String end[] = str[1].split("--");
                    year = Integer.parseInt(end[0])
                            -Integer.parseInt(start[0]);
                    month = Integer.parseInt(end[1])
                            -Integer.parseInt(start[1]);
                    day = Integer.parseInt(end[2])
                            -Integer.parseInt(start[2]);
                    int allday = 365*year + month*30 + day;
                    builder.delete(0,builder.length());
                    _tvtraveltime.setText(start[1]+"月"+start[2]+"日"+"——》"+end[1]+"月"+end[2]+"日");
                }
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


    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/EasytourServer/Province")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("NewTravel",responseData);
                    initJsonData(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析数据
     */
    private void initJsonData(String responseData){
        ArrayList<PlaceBean> placeBeen = parseJSONWithGSON(responseData);//用GSON转换成实例
        options1Items = placeBeen;

        StringBuilder builder = new StringBuilder();
        for (int i=0;i<placeBeen.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//城市列表，二级
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//省的地区列表，三级
            for(int j=0;j<placeBeen.get(i).getCity().size();j++){//遍历省下的所有城市
                String Cityname = placeBeen.get(i).getCity().get(j).getName();
                CityList.add(Cityname);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//城市的地区列表
                if(placeBeen.get(i).getCity().get(j).getArea() == null || placeBeen.get(i).getCity().get(j).getArea().size() == 0){
                    City_AreaList.add("");
                }else {
                    City_AreaList.addAll(placeBeen.get(i).getCity().get(j).getArea());
                    for(int k = 0;k<placeBeen.get(i).getCity().get(j).getArea().size();k++) {
                        builder.append(placeBeen.get(i).getName()).append("--")
                                .append(Cityname).append("--")
                                .append(placeBeen.get(i).getCity().get(j).getArea().get(k)).append("--")
                                .append(",");
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(CityList);    //城市
            options3Items.add(Province_AreaList);   //地区
        }
    }

    /**
     * GSON解析
     * @param jsonData  从网络获取到的json格式数据
     */
    private ArrayList<PlaceBean> parseJSONWithGSON(String jsonData){
        ArrayList<PlaceBean> detail =new ArrayList<>();
        try {
            JSONArray data = new JSONArray(jsonData);
            Gson gson = new Gson();
            for (int i=0;i<data.length();i++){
                PlaceBean entity = gson.fromJson(data.optJSONObject(i).toString(),PlaceBean.class);
                detail.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }


}
