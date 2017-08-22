package com.jiyun.qcloud.dashixummoban.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by 82年的笔记本 on 2017/8/12.
 */

public class RollAdapter extends StaticPagerAdapter {
    List<String> list;


    public RollAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        Glide.with(App.mBaseActivity).load(list.get(position)).into(imageView);
//        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
