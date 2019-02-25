package com.example.easytour.createluggage.selectyourluggage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.easytour.UserInfo;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.allluggage.MyDividerItemDecoration;
import com.example.easytour.R;
import com.example.easytour.allluggage.PlanBean;

public class SelectLuggages extends AppCompatActivity {
    private RecyclerView luggages_recyclerview;
    String Address; //旅行目的地
    String startDay;    //出发时间
    String endDay;  //返回时间
    UserInfo userInfo;  //user信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_luggages);
        Intent intent = getIntent();
        Address = intent.getStringExtra("dest");
        startDay = intent.getStringExtra("start");
        endDay = intent.getStringExtra("end");
        userInfo = (UserInfo) intent.getSerializableExtra("userinfo");
        initView();
        initAdapter();
    }
    private void initView(){
        luggages_recyclerview = (RecyclerView)findViewById(R.id.luggages_recyclerview);
        luggages_recyclerview.addItemDecoration(new MyDividerItemDecoration(this,MyDividerItemDecoration.VERTICAL_LIST));
    }
    private void initAdapter(){
        //布局管理器
        luggages_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //初始化和相关设置
        luggagesAdapter luggagesAdapter = new luggagesAdapter(this,Address,startDay,endDay);
        luggages_recyclerview.setAdapter(luggagesAdapter);
        luggagesAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(String all_select) {
                //数据存入数据库。同时穿梭
                //数据： dest-Address start-startDay end-endDay selectluggage-all_select
                PlanBean.AddnewUser(userInfo.getUserid().toString(),Address,startDay,endDay,all_select);
                Intent intent = new Intent(SelectLuggages.this, Allluggages.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);
                Toast.makeText(SelectLuggages.this,all_select+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
