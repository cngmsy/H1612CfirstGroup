package com.jiyun.qcloud.dashixummoban.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.entity.Order;

import java.util.List;

/**
 * Created by 82年的笔记本 on 2017/8/16.
 */

public class OrderAdapter extends BaseAdapter {
    List<Order.GoodsInfosBean> list;
    private MyHolder holder;

    public OrderAdapter(List<Order.GoodsInfosBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(App.mBaseActivity, R.layout.order_shop, null);
            holder = new MyHolder();
            holder.o_image = (ImageView) view.findViewById(R.id.o_image);
            holder.o_title = (TextView) view.findViewById(R.id.o_title);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.o_title.setText(list.get(i).getName());
        return view;
    }

    class MyHolder {
        TextView o_title;
        ImageView o_image;
    }
}
