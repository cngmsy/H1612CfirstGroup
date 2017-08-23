package com.jiyun.qcloud.dashixummoban.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.ShopFragmentAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;
import com.jiyun.qcloud.dashixummoban.ui.home1.HomeFragment;
import com.jiyun.qcloud.dashixummoban.ui.home1.HomePresenter;
import com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar.ContentFragment;
import com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar.GoodsFragment;
import com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar.GoodsPersenter;
import com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar.ShopsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingActivity extends BaseActivity {

    @BindView(R.id.iv_shopcar_back)
    ImageView ivShopcarBack;
    @BindView(R.id.tabl_shopcar)
    TabLayout tablShopcar;
    @BindView(R.id.vp_shop)
    ViewPager vpShop;

    private List<Fragment> fragments = new ArrayList<>();
    private ShopFragmentAdapter shopFragmentAdapter;
    private GoodsFragment goodsFragment;
    private ContentFragment contentFragment;
    private ShopsFragment shopsFragment;

    @Override
    protected void initData() {
        goodsFragment = new GoodsFragment();
        new GoodsPersenter(goodsFragment);
        contentFragment = new ContentFragment();
        shopsFragment = new ShopsFragment();
        fragments.add(goodsFragment);
        fragments.add(contentFragment);
        fragments.add(shopsFragment);
        shopFragmentAdapter = new ShopFragmentAdapter(getSupportFragmentManager(),fragments);
        vpShop.setAdapter(shopFragmentAdapter);
        tablShopcar.setupWithViewPager(vpShop);
        vpShop.setOffscreenPageLimit(fragments.size());
    }

    @Override
    protected void initView() {
//        GoodsFragment goodsFragment = (GoodsFragment) FragmentMager.getInstance().start(R.id.vp_shop, GoodsFragment.class, false).build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping;
    }

    @OnClick(R.id.iv_shopcar_back)
    public void onClick() {
        finish();
    }
}
