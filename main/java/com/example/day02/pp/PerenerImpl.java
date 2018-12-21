package com.example.day02.pp;

import com.example.day02.bean.MyData;
import com.example.day02.callback.MyCallBack;
import com.example.day02.model.ModelImpl;
import com.example.day02.vieww.IView;

public class PerenerImpl implements Perener{
    private IView iView;
    private ModelImpl model;

    public PerenerImpl(IView iView) {
        this.iView = iView;
        model=new ModelImpl();
    }

    @Override
    public void startRequest(String mUrl) {
        model.getData(mUrl, new MyCallBack() {
            @Override
            public void setSuccess(MyData data) {
                iView.setSuccess(data);
            }

            @Override
            public void setError(String error) {
                iView.setError(error);
            }
        });
    }
}
