package com.example.easytour.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easytour.BackListener;
import com.example.easytour.R;
import com.example.easytour.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText edit_userid;
    EditText edit_password;
    Button btn_Login;   //登录校验对应的账号是否在数据库，判断密码，如果错误toast
    Button btn_registe; //将信息录入数据库
    List<UserInfo> userdata;    //所有账户信息
    StringBuilder userbuilder;  //null时回调还没结束
    int tag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reint();
        userdata = new ArrayList<UserInfo>();
        initUser();
        edit_userid = (EditText)findViewById(R.id.edituser);
        edit_password = (EditText)findViewById(R.id.editpassword);
        btn_Login = (Button)findViewById(R.id.btnLogin);
        btn_registe = (Button)findViewById(R.id.btnRegiste);
        btn_Login.setOnClickListener(new View.OnClickListener() {   //验证信息
            @Override
            public void onClick(View v) {
                reint();
                userdata = new ArrayList<UserInfo>();
                initUser();
                int i;
                for(i=0;i<userdata.size();i++){
                    if(userdata.get(i).getUserid().toString().equals(edit_userid.getText().toString())){
                        if(userdata.get(i).getPassword().toString().equals(edit_password.getText().toString())){
                            Intent intent = new Intent();
                            intent.putExtra("userid",userdata.get(i).getUserid().toString());
                            intent.putExtra("password",userdata.get(i).getPassword().toString());
                            intent.putExtra("username",userdata.get(i).getUsername().toString());
                            setResult(RESULT_OK,intent);
                            finish();
                            break;
                        }else {
                            Toast.makeText(getApplicationContext(),"密码输入错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if( i == userdata.size()){
                    Toast.makeText(getApplicationContext(),"不存在此账户",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_registe.setOnClickListener(new View.OnClickListener() { //添加信息
            @Override
            public void onClick(View v) {
                int i;
                for(i=0;i<userdata.size();i++){
                    if(userdata.get(i).getUserid().toString().equals(edit_userid.getText().toString())){
                        Toast.makeText(getApplicationContext(),"此账户已存在,请换一个用户名",Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        if((i+1)<userdata.size()){
                            continue;
                        }else {
                            LRBean.AddnewUser(edit_userid.getText().toString(),edit_password.getText().toString());
                            userbuilder = null;
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void initUser(){
        //由于回调异步造成数据不同步引起错误，控制回调得到userbuilder再进行下去，强行同步
        while (userbuilder==null);
        String str1[] = userbuilder.toString().split(",");
        for(int i=0;i<str1.length;i++){
            String str2[] = str1[i].split("--");
            userdata.add(new UserInfo(str2[0],str2[1],str2[2]));
        }
    }
    private void reint(){
        LRBean.Selectall(new BackListener() {  //查询所有存在的账户的信息，存储在userdata
            @Override
            public void onFinsh(StringBuilder stringBuilder) {
                userbuilder = stringBuilder;
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
