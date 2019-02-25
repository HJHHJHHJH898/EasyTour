package com.example.easytour.sendluggage;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytour.MainActivity;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.sendluggage.city.CityList;
import com.example.easytour.test.CommentStudy;

import java.util.ArrayList;
import java.util.List;

public class luggage extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    UserInfo userInfo;
    TextView now_city ;
    TextView now_address;
    TextView after_address;
    Button btn_Ok;
    private RadioOnClick OnClicknow = new RadioOnClick(1);
    private RadioOnClick OnClickafter = new RadioOnClick(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luggage);

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
                    case R.id.nav_allluggage:
                        Intent intent = new Intent(luggage.this,Allluggages.class);
                        intent.putExtra("userinfo",userInfo);
                        startActivity(intent);
                        break;
                    case R.id.person:
                        Intent intentperson = new Intent(luggage.this,Personallnf.class);
                        intentperson.putExtra("userinfo",userInfo);
                        startActivity(intentperson);
                        break;
                    case R.id.nav_tip:
                        Intent intenttip = new Intent(luggage.this,CommentStudy.class);
                        intenttip.putExtra("userinfo",userInfo);
                        startActivity(intenttip);
                        break;
                    default:
                }
                return true;
            }
        });

        userInfo = (UserInfo) getIntent().getSerializableExtra("userinfo");
        now_city = (TextView)findViewById(R.id.tv_now_city);
        now_address = (TextView)findViewById(R.id.tv_now_address);
        after_address = (TextView)findViewById(R.id.tv_after_address);
        btn_Ok = (Button)findViewById(R.id.btn_OK);

        now_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNowCity();
            }
        });

        now_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNowAddress();
            }
        });

        after_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAfterAddress();
            }
        });

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now_city.getText().toString().equals("所在城市")|
                        now_address.getText().toString().equals("源地址")| after_address.getText().toString().equals("目的地址")){
                    Toast.makeText(getApplicationContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(luggage.this,Submission.class);
                    intent.putExtra("now_city",now_city.getText().toString());
                    intent.putExtra("now_address",now_address.getText().toString());
                    intent.putExtra("after_address",after_address.getText().toString());
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }
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
                Intent backintent = new Intent(luggage.this, MainActivity.class);
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

    public void selectNowCity(){
        //使用定位
        //城市定位、recyclerview
        Intent intent = new Intent(luggage.this,CityList.class);
        startActivityForResult(intent,1);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String ReturnCity = data.getStringExtra("city_return");
                    Toast.makeText(getApplicationContext(),ReturnCity,Toast.LENGTH_SHORT).show();
                    now_city.setText(ReturnCity);
                }
                break;
            default:
        }
    }

    public void selectNowAddress(){
        //使用弹窗
        //酒店、火车站、机场
        alert_edit(OnClicknow,now_address);

    }
    public void selectAfterAddress(){
        //使用弹窗
        //酒店、火车站、机场
        alert_edit(OnClickafter,after_address);
    }

    class RadioOnClick implements DialogInterface.OnClickListener{
        private int index;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            setIndex(which);
            dialog.dismiss();
        }

        public RadioOnClick(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public void alert_edit(final RadioOnClick OnClick, final TextView tv_address){
        //final DataBean bean = new DataBean(dbHelper);
        final EditText et = new EditText(this);
        final String[] address = {"酒店","火车站","机场"};
        final List<String> myaddress = new ArrayList<>();
        new AlertDialog.Builder(this).setTitle("请输入时间")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setSingleChoiceItems(address,OnClick.getIndex(),OnClick)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        tv_address.setText(address[OnClick.getIndex()]);
                    }
                })
                .show();
    }
}
