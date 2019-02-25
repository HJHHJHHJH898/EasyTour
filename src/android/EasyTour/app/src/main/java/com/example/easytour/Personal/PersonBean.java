package com.example.easytour.Personal;

import com.example.easytour.traveltips.SelectallCallbacklistener;
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
 * Created by 黄家慧 on 2018/12/14.
 */

public class PersonBean {
    public static final String WebSelectall = "http://10.0.2.2:8080" +
            "/EasytourServer/SelectAllperson.jsp";//全部显示
    public static final String WebUpdate = "http://10.0.2.2:8080" +
            "/EasytourServer/Updateperson.jsp";
    /**
     *
     * @return 所有tips的内容结果,!!!!!!!!!!!!!!!!!无法返回，使用回调！
     */
    public static void Selectall(final String userid,final SelectallCallbacklistener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebSelectall)
                            .post(requestBody)
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
        final List<PersonInfo> userList = gson.fromJson(gsonData,new TypeToken<List<PersonInfo>>(){}.getType());
        Iterator<PersonInfo> iter = userList.iterator();
        while (iter.hasNext()){
            PersonInfo info = iter.next();
            //builder.append(info.toString()).append("\n");
            builder.append(info.getUsername()).append("--");
            builder.append(info.getSex()).append("--");
            builder.append(info.getEmail()).append("--");
            builder.append(info.getPhone()).append("--");
            builder.append(info.getMysignature()).append("--");
        }
        return builder;
    }

    public static void Updateperson(final String userid,final String index,final String value){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .add("index",index)
                            .add("value",value)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebUpdate)
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
