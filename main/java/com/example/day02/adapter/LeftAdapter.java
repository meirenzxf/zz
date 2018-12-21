package com.example.day02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.day02.R;
import com.example.day02.bean.MyData;

import java.util.ArrayList;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<MyData.DataBean> list;
    private Context context;

    public LeftAdapter(ArrayList<MyData.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout,null);
        ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (adapterOnClick!=null){
            adapterOnClick.onClick(v, (Integer) v.getTag());
        }
    }

    public interface AdapterOnClick{
        void onClick(View v,int position);
    }
    private AdapterOnClick adapterOnClick;
    public void setOnClick(AdapterOnClick adapterOnClick){
        this.adapterOnClick=adapterOnClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.shangjia);
        }
    }
}
