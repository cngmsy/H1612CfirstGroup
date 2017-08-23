package com.jiyun.qcloud.dashixummoban.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;

import java.util.List;

/**
 * Created by 82年的笔记本 on 2017/8/12.
 */

public class Home_ListAdapter extends BaseAdapter {
    List<HomeBean.BodyBean.SellerBean> list;
    private MyHolder myHolder;

    public Home_ListAdapter(List<HomeBean.BodyBean.SellerBean> list) {
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

        if (view==null){
            view = View.inflate(App.mBaseActivity, R.layout.home_list_item, null);
            myHolder = new MyHolder();
             myHolder.name= view.findViewById(R.id.title);
            view.setTag(myHolder);
        }else {
            view.getTag();
        }
        if (list.get(i)!=null){
            myHolder.name.setText(list.get(i).getName());
        }


        return view;
    }
    class MyHolder{
        TextView name;
    }
}
