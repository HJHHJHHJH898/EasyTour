package com.example.easytour.Personal;

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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytour.MainActivity;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.test.CommentStudy;
import com.example.easytour.traveltips.SelectallCallbacklistener;

public class Personallnf extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    UserInfo userInfo;
    TextView tv_username;
    TextView tv_sex;
    TextView tv_email;
    TextView tv_phone;
    TextView tv_signature;
    PersonInfo personInfo;
    StringBuilder personbuilder;
    int flag[] = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personallnf);
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
        navigationView.setCheckedItem(R.id.person);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_allluggage:
                            Intent intent = new Intent(Personallnf.this,Allluggages.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        break;
                    case R.id.nav_send:
                            Intent intentsend = new Intent(Personallnf.this,luggage.class);
                        intentsend.putExtra("userinfo",userInfo);
                            startActivity(intentsend);
                        break;
                    case R.id.nav_tip:
                        Intent intenttip = new Intent(Personallnf.this,CommentStudy.class);
                        intenttip.putExtra("userinfo",userInfo);
                        startActivity(intenttip);
                        break;
                    default:
                }
                return true;
            }
        });

        userInfo = (UserInfo) getIntent().getSerializableExtra("userinfo");
        tv_username = (TextView)findViewById(R.id.tvusername);
        tv_sex = (TextView)findViewById(R.id.tvsex);
        tv_email = (TextView)findViewById(R.id.tvemail);
        tv_phone = (TextView)findViewById(R.id.tvphone);
        tv_signature = (TextView)findViewById(R.id.tvmysignature);

        initback();
        initperson();
        if(personInfo.getUsername()!=null){
            tv_username.setText(personInfo.getUsername().toString());
        }
        if(personInfo.getSex()!=null){
            tv_sex.setText(personInfo.getSex().toString());
        }
        if(personInfo.getEmail()!=null){
            tv_email.setText(personInfo.getEmail().toString());
        }
        if(personInfo.getPhone()!=null){
            tv_phone.setText(personInfo.getPhone().toString());
        }
        if(personInfo.getMysignature()!=null){
            tv_signature.setText(personInfo.getMysignature().toString());
        }

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag[0] = 1;
                alert_edit(tv_sex);
            }
        });
        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag[1] = 1;
                alert_edit(tv_email);
            }
        });
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag[2] = 1;
                alert_edit(tv_phone);
            }
        });
        tv_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag[3] = 1;
                alert_edit(tv_signature);
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
                if(flag[0]==1){
                    PersonBean.Updateperson(userInfo.getUserid().toString(),"sex",tv_sex.getText().toString());
                }
                if(flag[1]==1){
                    PersonBean.Updateperson(userInfo.getUserid().toString(),"email",tv_email.getText().toString());
                }
                if(flag[2]==1){
                    PersonBean.Updateperson(userInfo.getUserid().toString(),"phone",tv_phone.getText().toString());
                }
                if(flag[3]==1){
                    PersonBean.Updateperson(userInfo.getUserid().toString(),"mysignature",tv_signature.getText().toString());
                }
                personInfo = null;
                Intent backintent = new Intent(Personallnf.this, MainActivity.class);
                backintent.putExtra("userinfo",userInfo);
                startActivity(backintent);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void initperson(){
        while(personbuilder==null);

        String str1[] = personbuilder.toString().split("--");
        personInfo = new PersonInfo(str1[0],str1[1],str1[2],str1[3],str1[4]);
    }
    private void initback(){
        PersonBean.Selectall(userInfo.getUserid().toString(), new SelectallCallbacklistener() {
            @Override
            public void onFinsh(StringBuilder stringBuilder) {
                personbuilder = stringBuilder;
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
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
