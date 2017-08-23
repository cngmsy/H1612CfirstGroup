package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Data;
import com.jiyun.qcloud.dashixummoban.entity.Head;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by admin on 2017/8/19.
 */

public class DataStickyListAdapter extends BaseAdapter {
    private Context context;
    private List<Head> headList;
    private List<Data> dataList;

    //第一步，设置接口，这里方便在外面的activity或者fragment进行回调
    private View.OnClickListener onAddNum;
    private View.OnClickListener onSubNum;
    //第二步，设置接口方法
    public void setOnAddNum(View.OnClickListener onAddNum){
        this.onAddNum = onAddNum;
    }
    public void setOnSubNum(View.OnClickListener onSubNum){
        this.onSubNum = onSubNum;
    }

    public DataStickyListAdapter(Context context, List<Head> headList, List<Data> dataList) {
        this.context = context;
        this.headList = headList;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final StickHolder holder;
        if (convertView == null){
            holder = new StickHolder();
            convertView = View.inflate(context, R.layout.item_listright, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_goods_show);
            holder.add = (ImageView) convertView.findViewById(R.id.iv_goods_add);
            holder.add.setOnClickListener(onAddNum);
            holder.remove = (ImageView) convertView.findViewById(R.id.iv_goods_remove);
            holder.remove.setOnClickListener(onSubNum);
            holder.title = (TextView) convertView.findViewById(R.id.tv_goods_name);
            holder.content = (TextView) convertView.findViewById(R.id.tv_goods_content);
            holder.sell = (TextView) convertView.findViewById(R.id.tv_goods_sell);
            holder.newPrice = (TextView) convertView.findViewById(R.id.tv_goods_newprice);
            holder.oldPrice = (TextView) convertView.findViewById(R.id.tv_goods_oldprice);
            holder.count = (TextView) convertView.findViewById(R.id.tv_goods_count);
            convertView.setTag(holder);
        }else {
            holder = (StickHolder) convertView.getTag();
        }
        holder.title.setText(dataList.get(position).getTitle());
        String from = dataList.get(position).getContent();
        if (from != null) {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(from);
        } else {
            holder.content.setVisibility(View.GONE);
        }
        holder.sell.setText("月销售" + dataList.get(position).getSells() + "份");
        double newPrice = dataList.get(position).getNewPrice();
        BigDecimal bigDecimal = new BigDecimal(newPrice);
        double v = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.newPrice.setText("¥" + v);

        int old = dataList.get(position).getOldPrice();
        if (old != 0) {
            holder.oldPrice.setVisibility(View.VISIBLE);
            holder.oldPrice.setText("¥" + old);
            holder.oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.oldPrice.setVisibility(View.GONE);
        }
        Glide.with(context).load(dataList.get(position).getPic()).into(holder.imageView);
        holder.add.setTag(position);
        holder.remove.setTag(position);
        return convertView;
    }

    class StickHolder{
        private ImageView imageView,add,remove;
        private TextView title,content,sell,newPrice,oldPrice,count;
    }


    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        Data data = dataList.get(position);
        //把头(右侧标签)所在的下标与左侧相互关联
        Head head = headList.get(data.getHeadIndex());

        TextView tv = new TextView(context);
        tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
        //为右侧头添加左侧相对应的标签内容
        tv.setText(head.getInfo());
        tv.setBackgroundColor(Color.GRAY);
        return tv;
    }


    public long getHeaderId(int position) {
        int headId = dataList.get(position).getHeadId();
        return headId;
    }
}
