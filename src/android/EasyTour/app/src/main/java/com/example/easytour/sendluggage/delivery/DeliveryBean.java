package com.example.easytour.sendluggage.delivery;

import com.example.easytour.BackListener;
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

public class DeliveryBean {
    public static final String WebSelectall = "http://10.0.2.2:8080" +
            "/EasytourServer/SelectAlldelivery.jsp";//全部显示
    public static final String WebAdd = "http://10.0.2.2:8080" +
            "/EasytourServer/addDelivery.jsp";//添加订单
    public static void Selectall(final String userid, final BackListener listener){
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
        final List<DeliveryInfo> userList = gson.fromJson(gsonData,new TypeToken<List<DeliveryInfo>>(){}.getType());
        Iterator<DeliveryInfo> iter = userList.iterator();
        while (iter.hasNext()){
            DeliveryInfo info = iter.next();
            builder.append(info.getNow_city()).append("--");
            builder.append(info.getNow_address()).append("--");
            builder.append(info.getAfter_address()).append("--");
            builder.append(info.getNumber()).append("--");
            builder.append(info.getMoney()).append("--");
            builder.append(",");
        }
        return builder;
    }

    public void Add(final String userid, final String now_city,final String now_address,final String after_address,final int number,final String money){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .add("now_city",now_city)
                            .add("now_address",now_address)
                            .add("after_address",after_address)
                            .add("number",Integer.toString(number))
                            .add("money",money)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebAdd)
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
