package com.example.easytour.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytour.Login.Login;
import com.example.easytour.MainActivity;
import com.example.easytour.Personal.Personallnf;
import com.example.easytour.R;
import com.example.easytour.UserInfo;
import com.example.easytour.allluggage.Allluggages;
import com.example.easytour.sendluggage.luggage;
import com.example.easytour.traveltips.CreateTips;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentStudy extends AppCompatActivity{
    private UserInfo userInfo;
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentBean commentBean;
    private List<CommentDetailBean> commentsList;
    private BottomSheetDialog dialog;
    private DrawerLayout mDrawerLayout;
    private List<BufferInfo> bufferInfoList;
    StringBuilder AllBuffer;
    int ReplyNum;
    TextView tv_buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_study);
        userInfo = (UserInfo) getIntent().getSerializableExtra("userinfo");
        tv_buffer = (TextView)findViewById(R.id.tv_buffer);

        Selectall();
        while (commentsList==null);
        if(userInfo!=null){
            Selectallb(userInfo.getUserid().toString());
            while (bufferInfoList==null);
            if(bufferInfoList.size()!=0){
                ReplyNum = bufferInfoList.size();
                Iterator<BufferInfo> iter = bufferInfoList.iterator();
                AllBuffer = new StringBuilder();
                while (iter.hasNext()){
                    BufferInfo info = iter.next();
                    AllBuffer.append(info.getTipid()).append(",").append(info.getFrom()).append("-");
                }
            }
        }

        if(AllBuffer!=null){
            tv_buffer.setText("你有"+ReplyNum+"条消息");
        }

        tv_buffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AllBuffer!=null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                OkHttpClient client = new OkHttpClient();
                                RequestBody requestBody = new FormBody.Builder()
                                        .add("to",userInfo.getUserid())
                                        .build();
                                Request request = new Request.Builder()
                                        .url("http://10.0.2.2:8080/EasytourServer/deleteBuffer.jsp")
                                        .post(requestBody)
                                        .build();
                                Response response = client.newCall(request).execute();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    tv_buffer.setText("");
                }
            }
        });
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
        navigationView.setCheckedItem(R.id.nav_tip);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_allluggage:
                        if(userInfo !=null){
                            Intent intent = new Intent(CommentStudy.this,Allluggages.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(CommentStudy.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.nav_send:
                        if(userInfo !=null){
                            Intent intent = new Intent(CommentStudy.this,luggage.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(CommentStudy.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.person:
                        if(userInfo !=null){
                            Intent intent = new Intent(CommentStudy.this,Personallnf.class);
                            intent.putExtra("userinfo",userInfo);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(CommentStudy.this,Login.class);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case R.id.logoff:
//                            localBroadcastManager.unregisterReceiver(localReceiver);
                            Intent intent = new Intent(CommentStudy.this,Login.class);
                            startActivityForResult(intent,1);
                        break;
                    default:
                }
                return true;
            }
        });

        initView();
    }

    public void Selectall(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/EasytourServer/SelectAlltipcom.jsp")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseDarta = response.body().string();
                    parseJSONWithGSON(responseDarta);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void Selectallb(final String userid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("to",userid)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/EasytourServer/SelectBuffer.jsp")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseDarta = response.body().string();
                    parseJSONWithGSONb(responseDarta);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void parseJSONWithGSON(String gsonData){
        Gson gson = new Gson();
        CommentBean commentBean = gson.fromJson(gsonData, CommentBean.class);
        commentsList = commentBean.getData().getList();
    }

    public void parseJSONWithGSONb(String gsonData){
        Gson gson = new Gson();
        bufferInfoList = gson.fromJson(gsonData,new TypeToken<List<BufferInfo>>(){}.getType());
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
                Intent backintent = new Intent(CommentStudy.this, MainActivity.class);
                backintent.putExtra("userinfo",userInfo);
                startActivity(backintent);
                break;
            case R.id.Add:
                Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_SHORT).show();
                if(userInfo !=null){
                    Intent intent = new Intent(CommentStudy.this,CreateTips.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(CommentStudy.this,Login.class);
                    startActivityForResult(intent,1);
                }
                break;
            case R.id.Login:
                Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                if(userInfo !=null){
                    Intent intent = new Intent(CommentStudy.this,Personallnf.class);
                    intent.putExtra("userinfo",userInfo);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(CommentStudy.this,Login.class);
                    startActivityForResult(intent,2);
                }
                break;
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
            case 2:
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

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_lv_comment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("旅行Tips");
        initExpandableListView(commentsList);
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getId());
//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(CommentStudy.this,"点击了回复"+groupPosition+childPosition,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }

    /**
     * 评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    CommentDetailBean detailBean = new CommentDetailBean("小明", commentContent,"刚刚");
                    adapter.addTheCommentData(detailBean);
                    Toast.makeText(CommentStudy.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CommentStudy.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    /**
     * 回复框
     * @param position
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getAuthor() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String replyContent = commentText.getText().toString().trim();
                Calendar cal=Calendar.getInstance();
                int y=cal.get(Calendar.YEAR);
                int m=cal.get(Calendar.MONTH);
                int d=cal.get(Calendar.DATE);
                int h=cal.get(Calendar.HOUR_OF_DAY);
                int mi=cal.get(Calendar.MINUTE);
                int s=cal.get(Calendar.SECOND);
                String time = y+"-"+m+"-"+d+"-"+h+"-"+mi+"-"+s;
                if(!TextUtils.isEmpty(replyContent)){

                    dialog.dismiss();
                    ReplyDetailBean detailBean;
                    if(userInfo!=null){
                        detailBean = new ReplyDetailBean(userInfo.getUsername().toString(),userInfo.getUserid().toString(),
                                commentsList.get(position).getId(),replyContent,commentsList.get(position).getAuthor(),time);
                    } else {
                        detailBean = new ReplyDetailBean(replyContent);
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                OkHttpClient client = new OkHttpClient();
                                RequestBody requestBody = new FormBody.Builder()
                                        .add("tipid",Integer.toString(commentsList.get(position).getId()))
                                        .add("from",userInfo.getUserid().toString())
                                        .add("to",commentsList.get(position).getUserid())
                                        .add("content",replyContent)
                                        .build();
                                Request request = new Request.Builder()
                                        .url("http://10.0.2.2:8080/EasytourServer/addBuffer.jsp")
                                        .post(requestBody)
                                        .build();
                                Response response = client.newCall(request).execute();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    adapter.addTheReplyData(detailBean, position);
                    expandableListView.expandGroup(position);
                    Toast.makeText(CommentStudy.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CommentStudy.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }
}
