package com.example.easytour.Login;

import com.example.easytour.BackListener;
import com.example.easytour.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 黄家慧 on 2018/12/13.
 */

public class LRBean {
    public static final String WebSelectall = "http://10.0.2.2:8080" +
            "/EasytourServer/SelectallUser.jsp";//全部显示
    public static final String WebAdduser = "http://10.0.2.2:8080" +
            "/EasytourServer/addUser.jsp";//全部显示
    /**
     *
     * @return 所有tips的内容结果,!!!!!!!!!!!!!!!!!无法返回，使用回调！
     */
    public static void Selectall(final BackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(WebSelectall)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseDarta = response.body().string();
                    //网络连接成功，无法return
                    //return parseJSONWithGSON(responseDarta);
                    StringBuilder stringBuilder = parseJSONWithGSON(responseDarta);
                    if(listener!=null){
                        listener.onFinsh(stringBuilder);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (listener!=null){
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }
    /**
     * 使用GSON解析网络返回的json格式的文字
     * @param gsonData  json文件
     */
    private static StringBuilder parseJSONWithGSON(String gsonData){
        StringBuilder builder = new StringBuilder();
        Gson gson = new Gson();
        final List<UserInfo> userList = gson.fromJson(gsonData,new TypeToken<List<UserInfo>>(){}.getType());
        Iterator<UserInfo> iter = userList.iterator();
        while (iter.hasNext()){
            UserInfo info = iter.next();
            //builder.append(info.toString()).append("\n");
            builder.append(info.getUserid()).append("--");    //id
            builder.append(info.getPassword()).append("--");    //password
            builder.append(info.getUsername()).append("--");  //name
            builder.append(",");
        }
        return builder;
    }

    public static void AddnewUser(final String userid, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .add("password",password)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebAdduser)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
