package com.example.day02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.example.day02.bean.MyData;
import com.example.day02.weight.JiaJianView;

import java.util.ArrayList;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
  private ArrayList<MyData.DataBean.SpusBean> list;
  private Context context;

    public RightAdapter(ArrayList<MyData.DataBean.SpusBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.right_item,null);
        ViewHolder holder=new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getPic_url()).into(holder.image);
        holder.name.setText(list.get(position).getName());
        holder.yueshou.setText(list.get(position).getMonth_saled()+"");
        holder.price.setText(list.get(position).getSkus().get(0).getPrice());

        holder.jianjian.setCount(list.get(position).getPraise_num());
        holder.jianjian.getCount(new JiaJianView.CountCallBack() {
            @Override
            public void setCount(int count) {
list.get(position).setPraise_num(count);
adapterCallBack.shuaxin();
notifyDataSetChanged();
            }
        });


    }
    public float getGoodsPrice(){
        float price=0;
        for (int i=0;i<list.size();i++){
            price+=list.get(i).getPraise_num();
        }
        return price;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface AdapterCallBack {
        void shuaxin();
    }
    private AdapterCallBack adapterCallBack;
    public void setCallBack(AdapterCallBack adapterCallBack){
        this.adapterCallBack=adapterCallBack;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView yueshou;
        TextView price;
        JiaJianView jianjian;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.right_image);
            name=itemView.findViewById(R.id.sp_name);
            yueshou=itemView.findViewById(R.id.sp_yueshou);
            price=itemView.findViewById(R.id.sp_price);
            jianjian=itemView.findViewById(R.id.jiajian);
        }
        }

}
