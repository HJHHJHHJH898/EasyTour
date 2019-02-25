package com.example.easytour.traveltips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.test.CommentStudy;

public class CreateTips extends AppCompatActivity {
    Button btn_createtips;
    EditText edit_tipsdata;
    EditText edit_where;
    TipsBean tipsBean;
    UserInfo userInfo;
    Toolbar toolbarback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tips);
        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("userinfo");
        btn_createtips = (Button)findViewById(R.id.btn_createtips);
        edit_tipsdata = (EditText)findViewById(R.id.edit_tips);
        edit_where = (EditText)findViewById(R.id.edit_where);
        toolbarback = (Toolbar)findViewById(R.id.toolbar);
        toolbarback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent = new Intent(CreateTips.this, CommentStudy.class);
                backintent.putExtra("userinfo",userInfo);
                startActivity(backintent);
            }
        });
        btn_createtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsBean = new TipsBean();
                if(edit_tipsdata.getText()==null||edit_where.getText()==null){
                    Toast.makeText(getApplicationContext(),"请输入正确信息",Toast.LENGTH_SHORT).show();
                }else {
                    tipsBean.Add(userInfo.getUsername().toString(),edit_tipsdata.getText().toString(),edit_where.getText().toString(),userInfo.getUserid().toString());
                    Intent intent = new Intent(CreateTips.this, CommentStudy.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }
            }
        });
    }
}
