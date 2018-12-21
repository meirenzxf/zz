package com.example.day02.callback;

import com.example.day02.bean.MyData;

public interface MyCallBack {
    void setSuccess(MyData data);
    void setError(String error);
}
