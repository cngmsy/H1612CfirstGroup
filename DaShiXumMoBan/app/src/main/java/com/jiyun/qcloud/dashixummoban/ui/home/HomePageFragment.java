package com.jiyun.qcloud.dashixummoban.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chj on 2017/8/20.
 */

public class HomePageFragment extends BaseFragment implements XRecyclerView.LoadingListener,HomeContract.View {
    @BindView(R.id.homeBtns)
    Button homeBtns;

    @BindView(R.id.homeRecyclerView)
    XRecyclerView homeRecyclerView;
    private HomeContract.Presenter presenter;
    private List<Object> datas;



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        presenter.start();

    }

    @Override
    protected void initView(View view) {
        this.datas = new ArrayList<Object>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(manager);
        homeRecyclerView.setPullRefreshEnabled(true);
        homeRecyclerView.setLoadingMoreEnabled(false);
        homeRecyclerView.setLoadingListener(this);


    }

    @Override
    public void setBundle(Bundle bundle) {

    }





    @OnClick(R.id.homeBtns)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "00000000", Toast.LENGTH_SHORT).show();
        FragmentMager.getInstance().start(R.id.homeframe123,HomeDetailFragment.class,true).build();
       // FragmentBuilder.getInstance().init().initContainId(R.id.homeframe123).replace(HomeDetailFragment.class).build();
    }

    @Override
    public void showHomeListData(PandaHome pandaHome) {
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }
}
