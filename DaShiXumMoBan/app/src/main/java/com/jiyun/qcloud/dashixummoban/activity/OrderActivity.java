package com.jiyun.qcloud.dashixummoban.activity;

import android.widget.ImageView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 82年的笔记本 on 2017/8/22.
 */

public class OrderActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
