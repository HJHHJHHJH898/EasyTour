package com.example.easytour;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.easytour.AllIcon.FontManager;
import com.example.easytour.Login.Login;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.createluggage.NewTravel;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.test.CommentStudy;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    UserInfo userInfo;
    Button _btnstarttravel;
    ImageView _imgone;
    ImageView _imgtwo;
    ImageView _imgthree;
    ImageView _imgfour;
    ImageView _imgmainbag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_allluggage:
                        if(userInfo !=null){
                            Intent intent = new Intent(MainActivity.this,Allluggages.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.nav_send:
                        if(userInfo !=null){
                            Intent intent = new Intent(MainActivity.this,luggage.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.nav_tip:
                            Intent intenttip = new Intent(MainActivity.this,CommentStudy.class);
                            intenttip.putExtra("userinfo",userInfo);
                            startActivity(intenttip);
                        break;
                    case R.id.person:
                        if(userInfo !=null){
                            Intent intent = new Intent(MainActivity.this,Personallnf.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.logoff:
                        Intent intent = new Intent(MainActivity.this,Login.class);
                        startActivityForResult(intent,1);
                        break;
                    default:
                }
                return true;
            }
        });

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.activity_main), iconFont);
        Intent intent;
        if((intent = getIntent())!=null){
            userInfo = (UserInfo)intent.getSerializableExtra("userinfo");
        }
        _btnstarttravel = (Button)findViewById(R.id.btn_starttravel);
        _imgone = (ImageView)findViewById(R.id.main_button1);
        _imgtwo = (ImageView)findViewById(R.id.main_button2);
        _imgthree = (ImageView)findViewById(R.id.main_button3);
        _imgfour = (ImageView)findViewById(R.id.main_button4);
        _imgmainbag = (ImageView)findViewById(R.id.main_bag);
        _btnstarttravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInfo !=null){
                    Intent intent = new Intent(MainActivity.this, NewTravel.class);
                    //需要传递userinfo实例对象，网上查阅2中方法Serializable和Parceable
                    //使用序列化,需要userinfo类实现了序列化接口
                    intent.putExtra("userinfo", userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivityForResult(intent,1);
                }
            }
        });
        _imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInfo !=null){
                    Intent intent = new Intent(MainActivity.this,Allluggages.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivityForResult(intent,1);
                }
            }
        });
        _imgtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInfo !=null){
                    Intent intent = new Intent(MainActivity.this,luggage.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivityForResult(intent,1);
                }
            }
        });
        _imgthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CommentStudy.class);
                intent.putExtra("userinfo",userInfo);
                startActivity(intent);
            }
        });
        _imgfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInfo !=null){
                    Intent intent = new Intent(MainActivity.this,Personallnf.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivityForResult(intent,1);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String userid = data.getStringExtra("userid");
                    String password = data.getStringExtra("password");
                    String username = data.getStringExtra("username");
                    userInfo = new UserInfo();
                    userInfo.setUserid(userid);
                    userInfo.setPassword(password);
                    userInfo.setUsername(username);
                }
                break;
            default:
        }
    }
}
