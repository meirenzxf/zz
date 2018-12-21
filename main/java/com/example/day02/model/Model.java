package com.example.day02.model;

import com.example.day02.callback.MyCallBack;

public interface Model {
    void getData(String mUrl, MyCallBack callBack);
}
