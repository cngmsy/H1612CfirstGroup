package com.jiyun.qcloud.dashixummoban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.IBaseHttp;
import com.jiyun.qcloud.dashixummoban.modle.net.HttpFactory;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/8/25.
 */

public class InsertAddressActivity extends BaseActivity {
    @BindView(R.id.iv_address_back)
    ImageView ivAddressBack;
    @BindView(R.id.rl_address_head)
    RelativeLayout rlAddressHead;
    @BindView(R.id.iv_maplogo)
    ImageView ivMaplogo;
    @BindView(R.id.right_arrow_address)
    ImageView rightArrowAddress;
    @BindView(R.id.rl_address_addAddress)
    RelativeLayout rlAddressAddAddress;
    @BindView(R.id.tv_payway)
    TextView tvPayway;
    @BindView(R.id.tv_hongbao)
    TextView tvHongbao;
    @BindView(R.id.tv_daijinjuan)
    TextView tvDaijinjuan;
    @BindView(R.id.right_arrow_one)
    ImageView rightArrowOne;
    @BindView(R.id.right_arrow_two)
    ImageView rightArrowTwo;
    @BindView(R.id.right_arrow_three)
    ImageView rightArrowThree;
    @BindView(R.id.rl_address_middle)
    RelativeLayout rlAddressMiddle;
    @BindView(R.id.btn_address_submit)
    Button btnAddressSubmit;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    @BindView(R.id.ll_tjdd)
    LinearLayout llTjdd;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_sendmoney)
    TextView tvSendmoney;
    @BindView(R.id.activity_insert_address)
    RelativeLayout activityInsertAddress;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String all = intent.getStringExtra("all");
        tvJiesuan.setText(all);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_insert_address;
    }


    @OnClick({R.id.iv_address_back, R.id.rl_address_addAddress, R.id.btn_address_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_address_back:
                finish();
                break;
            case R.id.rl_address_addAddress:
                startActivity(new Intent(this, InfoActivity.class));
                break;
            case R.id.btn_address_submit:
                IBaseHttp ok = HttpFactory.createOK();
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().url("http://123.206.14.104:8080/TakeoutService/order?orderOverview=a").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String ss = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InsertAddressActivity.this, ss , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
        }
    }
}
