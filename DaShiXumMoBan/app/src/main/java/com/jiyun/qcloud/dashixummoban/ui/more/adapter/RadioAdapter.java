package com.jiyun.qcloud.dashixummoban.ui.more.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by lenovo on 2017/8/17.
 */

public class RadioAdapter extends BaseAdapter {
    Context context;
    ArrayList<Radio.DataBean.TrailersBean> arrayList;
    private MediaController mediaco;

    public RadioAdapter(Context context, ArrayList<Radio.DataBean.TrailersBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder der;
        if (convertView == null) {
            der=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.ben_tt, null);
            der.paly = (JCVideoPlayer)convertView.findViewById(R.id.videocontroller1);
            der.te= (TextView) convertView.findViewById(R.id.uuk);
            convertView.setTag(der);
        } else {
            der = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(arrayList.get(position).getCoverImg()).into(der.paly.ivThumb);
        String hightUrl = arrayList.get(position).getHightUrl();
        der.te.setText(arrayList.get(position).getMovieName());
        der.paly .setUp(hightUrl,null);
        der.paly.releaseAllVideos();

///xml布局里的
//        <VideoView
//        android:id="@+id/video1"
//        android:layout_width="match_parent"
//        android:layout_height="200dp" />

        ///Java局里的
//        String hightUrl = arrayList.get(position).getHightUrl();
//        der.te.setText(arrayList.get(position).getMovieName());
//        der.videf.setVideoPath(hightUrl);
//        mediaco=new MediaController(context);
//        der.videf.setMediaController(mediaco);
//        mediaco.setMediaPlayer(der.videf);
//        //让VideiView获取焦点
//        der.videf.requestFocus();
        return  convertView;
}
    class ViewHolder {
        JCVideoPlayer paly;
        TextView te;
    }
}
