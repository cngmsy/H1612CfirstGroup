package com.jiyun.qcloud.dashixummoban.ui.views;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by admin on 2017/8/14.
 */

public class CarImageView extends ImageView {
    public CarImageView(Context context) {
        super(context);
    }

    public CarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setMPointF(PointF pointF) {
        setX(pointF.x);
        setY(pointF.y);
    }
}
