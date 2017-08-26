package com.jiyun.qcloud.dashixummoban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.CheckOutAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.Data;
import com.jiyun.qcloud.dashixummoban.utils.ParcelableMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutActivity extends BaseActivity {

    @BindView(R.id.rl_checkout_head)
    RelativeLayout rlCheckoutHead;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_allmoeny)
    TextView tvAllmoeny;
    @BindView(R.id.rl_checkout_buttom)
    RelativeLayout rlCheckoutButtom;
    @BindView(R.id.rv_checkout)
    RecyclerView rvCheckout;
    @BindView(R.id.activity_checkout)
    RelativeLayout activityCheckout;
    @BindView(R.id.iv_checkout_back)
    ImageView ivCheckoutBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Map<Data, Integer> map = new HashMap<>();
    private CheckOutAdapter checkOutAdapter;
    private List<BigDecimal> all = new ArrayList<>();

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ParcelableMap parcelableMap = intent.getParcelableExtra("map");
        Map<Data, Integer> getMap = parcelableMap.getMap();
        if (null != getMap && getMap.size() > 0) {
            map.putAll(getMap);
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCheckout.setLayoutManager(manager);
        checkOutAdapter = new CheckOutAdapter(this, map);
        rvCheckout.setAdapter(checkOutAdapter);

        Set<Data> listBeen = map.keySet();
        for (Data list : listBeen) {
            double newPrice = list.getNewPrice();
            Integer integer = map.get(list);
            BigDecimal price = new BigDecimal(newPrice);
            BigDecimal count = new BigDecimal(integer);
            BigDecimal multiply = price.multiply(count);
            all.add(multiply);
        }
        BigDecimal submit = new BigDecimal(0);
        for (BigDecimal decimal : all) {
            submit = submit.add(decimal);
        }
        tvAllmoeny.setText(submit.setScale(2, BigDecimal.ROUND_HALF_DOWN) + "");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkout;
    }

    @OnClick({R.id.iv_checkout_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_checkout_back:
                finish();
                break;
            case R.id.btn_submit:
                Intent intent = new Intent(CheckoutActivity.this,InsertAddressActivity.class);
                intent.putExtra("all",tvAllmoeny.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
