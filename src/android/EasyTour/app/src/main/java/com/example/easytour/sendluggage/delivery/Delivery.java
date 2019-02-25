package com.example.easytour.sendluggage.delivery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytour.BackListener;
import com.example.easytour.MainActivity;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.test.CommentStudy;

import java.util.ArrayList;
import java.util.List;

public class Delivery extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private List<DeliveryInfo> _deliveryInfos;
    UserInfo userInfo;
    StringBuilder deliverybuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
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
        navigationView.setCheckedItem(R.id.nav_send);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_send:
                        Intent intent = new Intent(Delivery.this,luggage.class);
                        intent.putExtra("userinfo",userInfo);
                        startActivity(intent);
                        break;
                    case R.id.person:
                        Intent intentperson = new Intent(Delivery.this,Personallnf.class);
                        intentperson.putExtra("userinfo",userInfo);
                        startActivity(intentperson);
                        break;
                    case R.id.nav_tip:
                        Intent intenttip = new Intent(Delivery.this,CommentStudy.class);
                        intenttip.putExtra("userinfo",userInfo);
                        startActivity(intenttip);
                        break;
                    default:
                }
                return true;
            }
        });

        userInfo = (UserInfo)getIntent().getSerializableExtra("userinfo");

        initback(userInfo.getUserid().toString());
        _deliveryInfos = new ArrayList<DeliveryInfo>();
        initdeliver();
        _deliveryInfos.add(new DeliveryInfo(userInfo.getUserid().toString(),"123","123","1212",1,"1213"));

        RecyclerView deliveryrecyc = (RecyclerView)findViewById(R.id.Delivery);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        deliveryrecyc.setLayoutManager(manager);

        DeliveryAdapter deliveryAdapter = new DeliveryAdapter(_deliveryInfos);
        deliveryrecyc.setAdapter(deliveryAdapter);

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
                Intent intent = new Intent(Delivery.this, MainActivity.class);
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

    private void initback(String userid){
        DeliveryBean.Selectall(userid, new BackListener() {
            @Override
            public void onFinsh(StringBuilder stringBuilder) {
                deliverybuilder = stringBuilder;
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initdeliver(){
        while(deliverybuilder==null);
        String str1[] = deliverybuilder.toString().split(",");
        for(int i=0;i<str1.length;i++){
            String str2[] = str1[i].split("--");
            _deliveryInfos.add(new DeliveryInfo(userInfo.getUserid().toString(),str2[0],str2[1],str2[2],Integer.parseInt(str2[3]),str2[4]));
        }
        deliverybuilder = null;
    }
    /**
     * 订单列表，对控件定义以及初始化
     */
    private class DeliveryHolder extends RecyclerView.ViewHolder{
        TextView tv_Nowcity;
        TextView tv_Nowaddress;
        TextView tv_Afteraddress;
        TextView tv_Num;
        TextView tv_Money;
        public DeliveryHolder(View itemView) {
            super(itemView);
            tv_Nowcity = (TextView)itemView.findViewById(R.id.tvnowcity);
            tv_Nowaddress = (TextView)itemView.findViewById(R.id.tvnowaddress);
            tv_Afteraddress = (TextView)itemView.findViewById(R.id.tvafteraddress);
            tv_Num = (TextView)itemView.findViewById(R.id.tvnum);
            tv_Money = (TextView)itemView.findViewById(R.id.tvmoney);
        }

    }

    private class DeliveryAdapter extends RecyclerView.Adapter<DeliveryHolder>{
        public DeliveryAdapter(List<DeliveryInfo> deliveryInfos) {
            _deliveryInfos = deliveryInfos;
        }

        @Override
        public DeliveryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deliverylist,parent,false);
            DeliveryHolder holder = new DeliveryHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(DeliveryHolder holder, int position) {
            DeliveryInfo data = _deliveryInfos.get(position);
            holder.tv_Nowcity.setText(data.getNow_city().toString());
            holder.tv_Nowaddress.setText(data.getNow_address().toString());
            holder.tv_Afteraddress.setText(data.getAfter_address().toString());
            holder.tv_Num.setText(Integer.toString(data.getNumber()));
            holder.tv_Money.setText("总费用："+data.getMoney());
        }

        @Override
        public int getItemCount() {
            return _deliveryInfos.size();
        }
    }
}
