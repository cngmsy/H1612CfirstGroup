package com.jiyun.qcloud.dashixummoban.ui.home1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.activity.MapActivity;
import com.jiyun.qcloud.dashixummoban.activity.ShoppingActivity;
import com.jiyun.qcloud.dashixummoban.adapter.GridAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.Home_ListAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.RollAdapter;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;
import com.jiyun.qcloud.dashixummoban.main.MainActivity;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class HomeFragment extends BaseFragment implements XRecyclerView.LoadingListener, HomeContract.View, AbsListView.OnScrollListener,AdapterView.OnItemClickListener {
    @BindView(R.id.s_imag)
    ImageView sImag;
    @BindView(R.id.s_look)
    LinearLayout sLook;
    @BindView(R.id.linea)
    LinearLayout linearLayout;
    @BindView(R.id.listview)
    ListView mListview;
    boolean valid = true;
    @BindView(R.id.address)
    TextView address;
    Unbinder unbinder;
    private List<String> list = new ArrayList<>();
    private List<HomeBean.BodyBean.SellerBean> listview_list = new ArrayList<>();
    private RollAdapter rollAdapter;
    private Home_ListAdapter home_listAdapter;
    private RollPagerView rollPagerView;
    private GridView gridView;
    private List<HomeBean.HeadBean.CategorieListBean> grids;
    private GridAdapter gridAdapter;
    private HomeContract.Presenter presenter;
    private SparseArray recordSp = new SparseArray(0);
    private int mCurrentfirstVisibleItem = 0;

    @Override
    protected int getLayoutRes() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        home_listAdapter = new Home_ListAdapter(listview_list);
        mListview.setAdapter(home_listAdapter);
        View inflate = View.inflate(App.mBaseActivity, R.layout.home_head, null);
        linearLayout.getBackground().setAlpha(0);
        gridView = inflate.findViewById(R.id.grid);
        grids = new ArrayList<>();
        gridAdapter = new GridAdapter(grids);
        gridView.setAdapter(gridAdapter);
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rollPagerView = inflate.findViewById(R.id.roll);
        rollAdapter = new RollAdapter(list);
        rollPagerView.setPlayDelay(2000);
        rollPagerView.setAnimationDurtion(500);
        rollPagerView.setAdapter(rollAdapter);
        mListview.addHeaderView(inflate);
        mListview.setOnScrollListener(this);
        MainActivity activity = (MainActivity) getActivity();
        String send = activity.send();
        if (send != null) {
            address.setText(send);
        }
        lcation();
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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHomeListData(BaseBean baseBean) {
        Gson gson = new Gson();
        String data = baseBean.getData();
        HomeBean homeBean = gson.fromJson(data, HomeBean.class);
        HomeBean.HeadBean head = homeBean.getHead();
        List<HomeBean.HeadBean.CategorieListBean> categorieList = head.getCategorieList();
        grids.addAll(categorieList);
        List<HomeBean.HeadBean.PromotionListBean> promotionList = head.getPromotionList();
        for (HomeBean.HeadBean.PromotionListBean pBean : promotionList) {
            String pic = pBean.getPic();
            list.add(pic);

        }
        List<HomeBean.BodyBean> body = homeBean.getBody();
        for (HomeBean.BodyBean seller : body) {
            HomeBean.BodyBean.SellerBean seller1 = seller.getSeller();
            listview_list.add(seller1);
        }
        gridAdapter.notifyDataSetChanged();
        home_listAdapter.notifyDataSetChanged();
        rollAdapter.notifyDataSetChanged();

        mListview.setOnItemClickListener(this);
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    public void lcation() {
        if (valid) {
            valid = false;
        } else {
            return;
        }
        //初始化定位
        AMapLocationClient mLocationClient = new AMapLocationClient(App.mBaseActivity);
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    final String address = aMapLocation.getAddress();
                    if (address != null) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                App.mBaseActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        HomeFragment.this.address.setText(address);
                                    }
                                });

                            }
                        }, 2000);


                    }
                    Log.e("TAG", address);
                }
            }
        });
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mCurrentfirstVisibleItem = firstVisibleItem;
        View firstView = view.getChildAt(0);

        if (null != firstView) {
            ItemRecod itemRecord = (ItemRecod) recordSp.get(firstVisibleItem);
            if (null == itemRecord) {
                itemRecord = new ItemRecod();
            }
            itemRecord.height = firstView.getHeight();
            itemRecord.top = firstView.getTop();
            recordSp.append(firstVisibleItem, itemRecord);
            int ScrollY = getScrollY();
            if (ScrollY >= 0 && ScrollY <= 255) {
                linearLayout.getBackground().setAlpha(ScrollY);
            } else if (ScrollY > 255) {
                linearLayout.getBackground().setAlpha(255);
            }
        }
    }

    private int getScrollY() {
        int height = 0;
        for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
            ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
            if (itemRecod != null) {
                height += itemRecod.height;
            }
        }
        ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
        if (null == itemRecod) {
            itemRecod = new ItemRecod();
        }
        return height - itemRecod.top;


    }


    @OnClick(R.id.address)
    public void onViewClicked() {
        startActivity(new Intent(App.mBaseActivity, MapActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }

    class ItemRecod {
        int height = 0;
        int top = 0;
    }
}
