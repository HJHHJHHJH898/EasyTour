package com.example.easytour;

/**
 * Created by 黄家慧 on 2018/12/13.
 */

public interface BackListener {
    void onFinsh(StringBuilder stringBuilder);
    void onError(Exception e);
}
