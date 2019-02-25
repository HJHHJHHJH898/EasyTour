package com.example.easytour.allluggage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easytour.BackListener;
import com.example.easytour.Login.Login;
import com.example.easytour.MainActivity;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.UserInfo;
import com.example.easytour.createluggage.NewTravel;
import com.example.easytour.R;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.test.CommentStudy;

import java.util.ArrayList;
import java.util.List;

public class Allluggages extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    UserInfo userInfo;
    List<PlanInfo> list = new ArrayList<PlanInfo>();
    RecyclerView alltravel_recyclerview;
    StringBuilder planbuilder;
    Toolbar toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allluggages);
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
                    case R.id.nav_tip:
                            Intent intent = new Intent(Allluggages.this,CommentStudy.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        break;
                    case R.id.nav_send:
                            Intent intentsend = new Intent(Allluggages.this,luggage.class);
                            intentsend.putExtra("userinfo",userInfo);
                            startActivity(intentsend);
                        break;
                    case R.id.person:
                            Intent intentper = new Intent(Allluggages.this,Personallnf.class);
                            intentper.putExtra("userinfo",userInfo);
                            startActivity(intentper);
                        break;
                    default:
                }
                return true;
            }
        });

        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("userinfo");
        initplan(); //初始化出所有plan
        init();
        toolbarback = (Toolbar)findViewById(R.id.toolbar);

        toolbarback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(Allluggages.this, MainActivity.class);
                intentback.putExtra("userinfo",userInfo);
                startActivity(intentback);
            }
        });
        initView();
        initAdapter();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(getApplicationContext(),"Backup",Toast.LENGTH_SHORT).show();
                Intent backintent = new Intent(Allluggages.this, MainActivity.class);
                backintent.putExtra("userinfo",userInfo);
                startActivity(backintent);
                break;
            case R.id.Add:
                Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Allluggages.this,NewTravel.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);
                break;
            case R.id.Login:
                Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                Intent intentper = new Intent(Allluggages.this,Personallnf.class);
                intentper.putExtra("userinfo",userInfo);
                startActivity(intentper);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void initView(){
        alltravel_recyclerview = (RecyclerView)findViewById(R.id.alltravel_recyclerview);
        alltravel_recyclerview.addItemDecoration(new MyDividerItemDecoration(this,MyDividerItemDecoration.VERTICAL_LIST));
    }
    private void initAdapter(){
        //布局管理器
        alltravel_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //初始化和相关设置
        alltravelAdapter alltravelAdapter = new alltravelAdapter(this,list);
        alltravel_recyclerview.setAdapter(alltravelAdapter);
    }

    public void alert_edit(){
        //final DataBean bean = new DataBean(dbHelper);
        final EditText et = new EditText(this);
        final String[] luggage = {"衣服","水杯","车票"};
        final List<String> myluggages = new ArrayList<>();
        new AlertDialog.Builder(this).setTitle("请输入时间")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setMultiChoiceItems(luggage,null,new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            myluggages.add(luggage[which]);
                        } else {
                            myluggages.remove(luggage[which]);
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        Toast.makeText(getApplicationContext(), "OK",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
    private void init(){
        while (planbuilder==null);
        if (planbuilder.length()!=0){
            String str1[] = planbuilder.toString().split(",");
            for(int i=0;i<str1.length;i++){
                String str2[] = str1[i].split("--");
                PlanInfo planInfo = new PlanInfo(str2[0],str2[1],str2[2],str2[3]);
                list.add(planInfo);
            }
            planbuilder = null;
        }
    }
    private void initplan(){
        PlanBean.SelectPlan(userInfo.getUserid().toString(), new BackListener() {
            @Override
            public void onFinsh(StringBuilder stringBuilder) {
                planbuilder = stringBuilder;
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
