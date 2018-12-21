package com.example.day02.vieww;

import com.example.day02.bean.MyData;

public interface IView {
    void setSuccess(MyData data);
    void setError(String error);
}
