package com.example.easytour.allluggage;

/**
 * Created by 黄家慧 on 2018/12/13.
 */

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
 * 计划类的增加查询
 */
public class PlanBean {
    public static final String WebSelectplan = "http://10.0.2.2:8080" +
            "/EasytourServer/SelectAllPlan.jsp";//全部显示
    public static final String WebAddplan = "http://10.0.2.2:8080" +
            "/EasytourServer/addPlan.jsp";//全部显示
    public static void SelectPlan(final String userid, final BackListener backListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebSelectplan)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseResult = response.body().string();
                    if(backListener!=null){
                        backListener.onFinsh(parseJSONWithGSON(responseResult));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (backListener!=null){
                        backListener.onError(e);
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
        final List<PlanInfo> userList = gson.fromJson(gsonData,new TypeToken<List<PlanInfo>>(){}.getType());
        Iterator<PlanInfo> iter = userList.iterator();
        while (iter.hasNext()){
            PlanInfo info = iter.next();
            builder.append(info.getDest()).append("--");
            builder.append(info.getStarttime()).append("--");
            builder.append(info.getEndtime()).append("--");
            builder.append(info.getChoice_luggage());
            builder.append(",");
        }
        return builder;
    }

    public static void AddnewUser(final String userid, final String dest, final String starttime, final String endtime, final String choice_luggage){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userid",userid)
                            .add("dest",dest)
                            .add("starttime",starttime)
                            .add("endtime",endtime)
                            .add("choice_luggage",choice_luggage)
                            .build();
                    Request request = new Request.Builder()
                            .url(WebAddplan)
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
