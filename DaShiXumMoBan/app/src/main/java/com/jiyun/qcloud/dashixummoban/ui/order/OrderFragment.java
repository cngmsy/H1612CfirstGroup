package com.jiyun.qcloud.dashixummoban.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.activity.OrderActivity;
import com.jiyun.qcloud.dashixummoban.adapter.OrderAdapter;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Order;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 82年的笔记本 on 2017/8/22.
 */

public class OrderFragment extends BaseFragment implements XRecyclerView.LoadingListener, OrderContract.View {
    @BindView(R.id.listview)
    ListView listview;
    List<Order.GoodsInfosBean> list = new ArrayList<>();
    private OrderAdapter orderAdapter;
    private OrderContract.Presenter presenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.order_fargment;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        orderAdapter = new OrderAdapter(list);
        listview.setAdapter(orderAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(App.mBaseActivity, OrderActivity.class));
            }
        });
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHomeListData(BaseBean baseBean) {
        String data = baseBean.getData();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Order>>() {
        }.getType();
        List<Order> orders = gson.fromJson(data, type);
        for (Order o : orders
                ) {
            List<Order.GoodsInfosBean> goodsInfos = o.getGoodsInfos();
            list.addAll(goodsInfos);
        }
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }
}
