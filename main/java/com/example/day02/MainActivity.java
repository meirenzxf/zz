package com.example.day02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.day02.adapter.LeftAdapter;
import com.example.day02.adapter.RightAdapter;
import com.example.day02.bean.MyData;
import com.example.day02.pp.PerenerImpl;
import com.example.day02.vieww.IView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IView {

    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private TextView price;
    private TextView jeisuan;
    private RelativeLayout lay;
    private RecyclerView recy1;
    private TextView right_shangjia;
    private RecyclerView recy2;
    private ArrayList<MyData.DataBean> list = new ArrayList<>();
    private ArrayList<MyData.DataBean.SpusBean> sups = new ArrayList<>();
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private PerenerImpl perener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recy1.setLayoutManager(new LinearLayoutManager(this));
        recy2.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftAdapter(list, this);
        rightAdapter = new RightAdapter(sups, this);
        recy1.setAdapter(leftAdapter);
        recy2.setAdapter(rightAdapter);
        perener = new PerenerImpl(this);
        perener.startRequest(mUrl);
        leftAdapter.setOnClick(new LeftAdapter.AdapterOnClick() {
            @Override
            public void onClick(View v, int position) {
                sups.clear();
                sups.addAll(list.get(position).getSpus());
                for (int i = 0; i < sups.size(); i++) {
                    sups.get(i).setPraise_num(0);
                }
                right_shangjia.setText(list.get(position).getName());
                rightAdapter.notifyDataSetChanged();
                flushBootm();
            }
        });
        rightAdapter.setCallBack(new RightAdapter.AdapterCallBack() {
            @Override
            public void shuaxin() {
                flushBootm();
            }
        });

    }

    public void flushBootm() {
        price.setText(rightAdapter.getGoodsPrice() + "");
    }

    private void initView() {
        price = (TextView) findViewById(R.id.price);
        jeisuan = (TextView) findViewById(R.id.jeisuan);
        lay = (RelativeLayout) findViewById(R.id.lay);
        recy1 = (RecyclerView) findViewById(R.id.recy1);
        right_shangjia = (TextView) findViewById(R.id.right_shangjia);
        recy2 = (RecyclerView) findViewById(R.id.recy2);
    }

    @Override
    public void setSuccess(MyData data) {
        MyData data1 = data;
        list.addAll(data1.getData());
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String error) {

    }
}