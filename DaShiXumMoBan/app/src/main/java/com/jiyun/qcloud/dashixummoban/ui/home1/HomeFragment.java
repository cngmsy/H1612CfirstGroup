package com.jiyun.qcloud.dashixummoban.ui.home1;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.GridAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.Home_ListAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.RollAdapter;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View,AbsListView.OnScrollListener {
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.s_imag)
    ImageView sImag;
    @BindView(R.id.s_look)
    LinearLayout sLook;
    @BindView(R.id.linea)
    LinearLayout linearLayout;
    @BindView(R.id.listview)
    ListView mListview;
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
        gridView = (GridView) inflate.findViewById(R.id.grid);
        grids = new ArrayList<>();
        gridAdapter = new GridAdapter(grids);
        gridView.setAdapter(gridAdapter);
        gridView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rollPagerView = (RollPagerView) inflate.findViewById(R.id.roll);
        rollAdapter = new RollAdapter(list);
        rollPagerView.setPlayDelay(2000);
        rollPagerView.setAnimationDurtion(500);
        rollPagerView.setAdapter(rollAdapter);
        mListview.addHeaderView(inflate);
        mListview.setOnScrollListener(this);
    }

    @Override
    public void setBundle(Bundle bundle) {

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
        Gson gson=new Gson();
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
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

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

    class ItemRecod {
        int height = 0;
        int top = 0;
    }
}
