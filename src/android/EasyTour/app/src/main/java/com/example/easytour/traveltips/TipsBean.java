package com.example.easytour.traveltips;

/**
 * Created by 黄家慧 on 2018/12/11.
 */

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
 * 连接tips数据库的工具类
 */
public class TipsBean {
    public static final String WebSelectall = "http://10.0.2.2:8080" +
            "/EasytourServer/SelectAlltips.jsp";//全部显示
    public static final String WebAddtip = "http://10.0.2.2:8080" +
            "/EasytourServer/addTips.jsp";//全部显示
    /**
     *
     * @return 所有tips的内容结果,!!!!!!!!!!!!!!!!!无法返回，使用回调！
     */
    public static void Selectall(final SelectallCallbacklistener listener){
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
                    if(listener!=null){
                        listener.onFinsh(parseJSONWithGSON(responseDarta));
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
        final List<TipsInfo> userList = gson.fromJson(gsonData,new TypeToken<List<TipsInfo>>(){}.getType());
        Iterator<TipsInfo> iter = userList.iterator();
        while (iter.hasNext()){
            TipsInfo info = iter.next();
            //builder.append(info.toString()).append("\n");
            builder.append(info.getId()).append("--");    //id
            builder.append(info.getAuthor()).append("--");    //author
            builder.append(info.getWherewant()).append("--");  //where
            builder.append(info.getTime()).append(","); //time
        }
        return builder;
    }

    /**
     *  发表
     */
    public void Add(final String username, final String tipsdata, final String where, final String userid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("author",username)
                            .add("where",where)
                            .add("tipsdata",tipsdata)
                            .add("userid",userid)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebAddtip)
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
