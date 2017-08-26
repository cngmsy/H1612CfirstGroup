package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Data;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017/8/14.
 */

public class CheckOutAdapter extends RecyclerView.Adapter {
    private Context context;
    private Map<Data,Integer> map;

    public CheckOutAdapter(Context context, Map<Data, Integer> map) {
        this.context = context;
        this.map = map;
    }
    class CheckHolder extends RecyclerView.ViewHolder{
        private ImageView show;
        private TextView name,content,count,price;
        public CheckHolder(View itemView) {
            super(itemView);
            show = (ImageView) itemView.findViewById(R.id.iv_check_show);
            name = (TextView) itemView.findViewById(R.id.tv_check_name);
            content = (TextView) itemView.findViewById(R.id.tv_check_content);
            count = (TextView) itemView.findViewById(R.id.tv_check_count);
            price = (TextView) itemView.findViewById(R.id.tv_tv_check_newprice);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_checkrecycler,null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new CheckHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CheckHolder checkHolder = (CheckHolder) holder;
        Set<Data> listBeen = map.keySet();
        List<Data> mList = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (Data bean : listBeen){
            Integer integer = map.get(bean);
            counts.add(integer);
            mList.add(bean);
        }

        checkHolder.count.setText(counts.get(position)+"");
        Glide.with(context).load(mList.get(position).getPic()).into(checkHolder.show);
        checkHolder.name.setText(mList.get(position).getTitle());
        checkHolder.content.setText(mList.get(position).getContent());
        double newPrice = mList.get(position).getNewPrice();
        Log.e("TAG",newPrice+"+++++++++++");
        BigDecimal bigDecimal = new BigDecimal(newPrice);
        double v = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        checkHolder.price.setText(v+"");
    }

    @Override
    public int getItemCount() {
        return map.size();
    }
}
