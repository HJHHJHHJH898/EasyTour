package com.example.easytour.sendluggage.city;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easytour.R;

import java.util.ArrayList;
import java.util.List;

public class CityList extends AppCompatActivity {

    private List<CityInfo> _citydata;

    EditText Edit_SelectCity;   //搜索框
    TextView Now_City;  //当前城市
    RecyclerView CityRecyclerview;  //城市列表

    CityCallbackListener cityCallbackListener;//回调接口，返回选择的城市

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        Edit_SelectCity = (EditText)findViewById(R.id.edit_select_city);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);    //搜索框默认开始不弹出

        Now_City = (TextView)findViewById(R.id.tv_citylist_now_city);   //定位

        CityRecyclerview = (RecyclerView)findViewById(R.id.recycle_city);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CityRecyclerview.setLayoutManager(manager);
        initData();

        cityAdapter adapter = new cityAdapter(_citydata);
        CityRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickLitener(new CityCallbackListener() {
            @Override
            public void OnItemClick(String aCity) {
                Intent intent = new Intent();
                intent.putExtra("city_return",aCity);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initData(){
        _citydata = new ArrayList<CityInfo>();
        _citydata.add(new CityInfo("A","阿拉善盟","鞍山","安庆","安阳","阿坝"));
        _citydata.add(new CityInfo("B","北京","保定","包头","本溪","白山"));
        _citydata.add(new CityInfo("C","重庆","成都","长沙","常州","长春"));
        _citydata.add(new CityInfo("D","大连","大庆","东莞","大同","丹东"));
        _citydata.add(new CityInfo("E","鄂尔多斯","鄂州","恩施","峨眉山","额尔古纳"));
        _citydata.add(new CityInfo("F","福州","佛山","抚顺","阜新","阜阳"));
        _citydata.add(new CityInfo("G","广州","桂林","贵阳","赣州","贵港"));
        _citydata.add(new CityInfo("H","杭州","合肥","海口","葫芦岛","哈尔滨"));
        _citydata.add(new CityInfo("I","","","","",""));
        _citydata.add(new CityInfo("J","济南","焦作","锦州","九江","晋城"));
        _citydata.add(new CityInfo("K","昆明","开封","克拉玛依","克州","昆山"));
        _citydata.add(new CityInfo("L","廊坊","临汾","吕梁","辽阳","辽源"));
        _citydata.add(new CityInfo("M","牡丹江","马鞍山","茂名","梅州","绵阳"));
        _citydata.add(new CityInfo("N","宁波","南京","南通","南昌","南宁"));
        _citydata.add(new CityInfo("O","","","","",""));
        _citydata.add(new CityInfo("P","盘锦","莆田","平顶山","濮阳","攀枝花"));
        _citydata.add(new CityInfo("Q","青岛","齐齐哈尔","泉州","秦皇岛","七台河"));
        _citydata.add(new CityInfo("R","日照","日喀则","瑞安","仁怀","荣成"));
        _citydata.add(new CityInfo("S","上海","深圳","沈阳","石家庄","苏州"));
        _citydata.add(new CityInfo("T","天津","太原","唐山","通辽","铁岭"));
        _citydata.add(new CityInfo("U","","","","",""));
        _citydata.add(new CityInfo("V","","","","",""));
        _citydata.add(new CityInfo("W","无锡","武汉","芜湖","温州","乌海"));
        _citydata.add(new CityInfo("X","西安","厦门","新乡","香港","徐州"));
        _citydata.add(new CityInfo("Y","烟台","扬州","阳泉","运城","营口"));
        _citydata.add(new CityInfo("Z","郑州","珠海","中山","张家口","镇江"));
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        TextView Zimu;
        TextView Location1;
        TextView Location2;
        TextView Location3;
        TextView Location4;
        TextView Location5;
        public MyHolder(View itemView) {
            super(itemView);
            Zimu = (TextView)itemView.findViewById(R.id.tv_Zimu);
            Location1 = (TextView)itemView.findViewById(R.id.tvLocation1);
            Location2 = (TextView)itemView.findViewById(R.id.tvLocation2);
            Location3 = (TextView)itemView.findViewById(R.id.tvLocation3);
            Location4 = (TextView)itemView.findViewById(R.id.tvLocation4);
            Location5 = (TextView)itemView.findViewById(R.id.tvLocation5);
            Location1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cityCallbackListener!=null){
                        cityCallbackListener.OnItemClick(Location1.getText().toString());
                    }
                }
            });
            Location2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cityCallbackListener!=null){
                        cityCallbackListener.OnItemClick(Location2.getText().toString());
                    }
                }
            });
            Location3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cityCallbackListener!=null){
                        cityCallbackListener.OnItemClick(Location3.getText().toString());
                    }
                }
            });
            Location4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cityCallbackListener!=null){
                        cityCallbackListener.OnItemClick(Location4.getText().toString());
                    }
                }
            });
            Location5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cityCallbackListener!=null){
                        cityCallbackListener.OnItemClick(Location5.getText().toString());
                    }
                }
            });
        }
    }
    private  class cityAdapter extends RecyclerView.Adapter<MyHolder>{
        public cityAdapter(List<CityInfo> stationInfoList) {
            _citydata = stationInfoList;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.citylist,parent,false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            CityInfo station = _citydata.get(position);

            holder.Zimu.setText(station.getZimu());
            holder.Location1.setText(station.getLocation1());
            holder.Location2.setText(station.getLocation2());
            holder.Location3.setText(station.getLocation3());
            holder.Location4.setText(station.getLocation4());
            holder.Location5.setText(station.getLocation5());
        }

        @Override
        public int getItemCount() {
            return _citydata.size();
        }

        public void setOnItemClickLitener(CityCallbackListener clickLitener) {
            cityCallbackListener = clickLitener;
        }
    }
}
