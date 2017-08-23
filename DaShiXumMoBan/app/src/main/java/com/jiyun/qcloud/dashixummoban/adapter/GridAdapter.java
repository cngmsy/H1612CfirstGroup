package com.jiyun.qcloud.dashixummoban.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;

import java.util.List;

/**
 * Created by 82年的笔记本 on 2017/8/13.
 *
 *
 */

public class GridAdapter extends BaseAdapter {
    List<HomeBean.HeadBean.CategorieListBean> list;

    private MyHolder myHolder;
    public GridAdapter(List<HomeBean.HeadBean.CategorieListBean> list) {
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
            view = View.inflate(App.mBaseActivity, R.layout.grid_item, null);
            myHolder = new MyHolder();
            myHolder.textView = (TextView) view.findViewById(R.id.text);
            myHolder.imageView= (ImageView) view.findViewById(R.id.image);
            view.setTag(myHolder);
        } else {
            view.getTag();
        }
        myHolder.textView.setText(list.get(i).getName());

        Glide.with(App.mBaseActivity).load(list.get(i).getPic()).into(myHolder.imageView);


        return view;
    }

    class MyHolder {
        ImageView imageView;
        TextView textView;
    }
}
