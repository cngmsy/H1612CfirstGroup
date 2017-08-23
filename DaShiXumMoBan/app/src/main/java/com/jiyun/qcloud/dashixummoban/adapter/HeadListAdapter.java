package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.entity.Head;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */

public class HeadListAdapter extends BaseAdapter {
    private Context context;
    private List<Head> headList;
    private int mSelectedPosition;

    public HeadListAdapter(Context context, List<Head> headList) {
        this.context = context;
        this.headList = headList;
    }

    @Override
    public int getCount() {
        return headList.size();
    }

    @Override
    public Object getItem(int position) {
        return headList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        //设置内容
        tv.setText(headList.get(position).getInfo());
        //宽高参数
        tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        //字体颜色
        tv.setTextColor(Color.BLACK);
        //设置选中状态和位选中状态下的item条目的背景颜色
        if (position == mSelectedPosition) {
            tv.setBackgroundColor(Color.WHITE);
        } else {
            tv.setBackgroundColor(Color.GRAY);
        }
        return tv;
    }

    /**
     * 左边条目的选择
     **/
    public void setSelectedPosition(int selectedPosition) {
        /**
         * 如果点击的条目已经是选中状态则不用刷新适配器
         * 如果不是则刷新适配器（改变背景颜色）
         */
        if (mSelectedPosition == selectedPosition){
            return;   //不用刷新
        }
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}
