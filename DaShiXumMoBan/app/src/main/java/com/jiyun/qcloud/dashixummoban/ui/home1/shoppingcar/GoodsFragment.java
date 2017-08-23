package com.jiyun.qcloud.dashixummoban.ui.home1.shoppingcar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.activity.CheckoutActivity;
import com.jiyun.qcloud.dashixummoban.adapter.DataStickyListAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.HeadListAdapter;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Data;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.ui.views.CarImageView;
import com.jiyun.qcloud.dashixummoban.utils.ParcelableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static android.R.attr.animation;

/**
 * Created by admin on 2017/8/22.
 */

public class GoodsFragment extends BaseFragment implements GoodsContract.View,AdapterView.OnItemClickListener, Animator.AnimatorListener, AbsListView.OnScrollListener,XRecyclerView.LoadingListener {
    @BindView(R.id.lv_goods_left)
    ListView lvGoodsLeft;
    @BindView(R.id.shlv_goods_right)
    StickyListHeadersListView shlvGoodsRight;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    Unbinder unbinder;
    @BindView(R.id.rl_father)
    RelativeLayout rlFather;
    private GoodsContract.Persenter persenter;
    private List<Data> dataList = new ArrayList<>();
    private List<Head> headList = new ArrayList<>();
    private HeadListAdapter headListAdapter;
    private DataStickyListAdapter stickyListAdapter;
    private Intent intent;
    private Map<Data, Integer> map = new HashMap();
    private int num = 0;
    private boolean isScroll = false;
    private BadgeView bind;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initData() {
        persenter.start();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public void showData(BaseBean baseBean) {

    }

    @Override
    public void getList(List<Head> headList, List<Data> dataList) {
        this.headList = headList;
        this.dataList = dataList;

        headListAdapter = new HeadListAdapter(App.mBaseActivity, headList);
        lvGoodsLeft.setAdapter(headListAdapter);

        stickyListAdapter = new DataStickyListAdapter(App.mBaseActivity, headList, dataList);

        shlvGoodsRight.setAdapter(stickyListAdapter);
        lvGoodsLeft.setOnItemClickListener(GoodsFragment.this);
        shlvGoodsRight.setOnScrollListener(GoodsFragment.this);

        stickyListAdapter.setOnAddNum(listener);
        stickyListAdapter.setOnSubNum(listener);
    }

    /**
     * 这里处理我们listview item里面的监听事件
     */
    protected View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            int key = v.getId();
            switch (key) {
                case R.id.iv_goods_add: //点击添加数量按钮，执行相应的处理
                    // 获取 Adapter 中设置的 Tag
                    if (tag != null && tag instanceof Integer) { //解决问题：如何知道你点击的按钮是哪一个列表项中的，通过Tag的position
                        int position = (Integer) tag;
                        //更改集合的数据
                        int num = Double.valueOf(dataList.get(position).getCount()).intValue();
                        num++;
                        dataList.get(position).setCount(num); //修改集合中商品数量
                        //解决问题：点击某个按钮的时候，如果列表项所需的数据改变了，如何更新UI
                        stickyListAdapter.notifyDataSetChanged();
                    }
                    break;
                case R.id.iv_goods_remove: //点击减少数量按钮 ，执行相应的处理
                    // 获取 Adapter 中设置的 Tag
                    if (tag != null && tag instanceof Integer) {
                        int position = (Integer) tag;
                        //更改集合的数据
                        int num = Double.valueOf(dataList.get(position).getCount()).intValue();
                        if (num > 0) {
                            num--;
                            dataList.get(position).setCount(num); //修改集合中商品数量
                            stickyListAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    };

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
    public void setPresenter(GoodsContract.Persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.iv_car)
    public void onClick() {
        if (null != map && map.size() > 1) {
            ParcelableMap parcelableMap = new ParcelableMap();
            parcelableMap.setMap(map);
            intent.putExtra("map", parcelableMap);
            getActivity().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "请先添加商品", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void click(View v, int position, final ImageView remove, final TextView count) {
//        intent = new Intent(getActivity(), CheckoutActivity.class);
//        Data data1 = dataList.get(position);
//        Integer beanCount = map.get(data1);
//        if (beanCount == null) {
//            map.put(data1, 1);
//        } else {
//            beanCount++;
//            map.put(data1, beanCount);
//        }
//        for (int i = 0; i < headList.size(); i++) {
//            String s = count.getText().toString();
//            if (s.isEmpty()) {
//                count.setText(1 + "");
//            } else {
//                int j = Integer.parseInt(s);
//                j++;
//                count.setText(j + "");
//            }
//        }
//        num++;
//        bind = BadgeFactory.create(getContext())
//                .setTextColor(Color.WHITE)
//                .setWidthAndHeight(25, 25)
//                .setBadgeBackground(Color.RED)
//                .setTextSize(10)
//                .setBadgeGravity(Gravity.LEFT)
//                .setBadgeCount(num)
//                .setShape(BadgeView.SHAPE_CIRCLE)
//                .bind(ivCar);
//
//        remove.setVisibility(View.VISIBLE);
//        count.setVisibility(View.VISIBLE);
//
//        remove.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                num--;
//                if (num <=  0) {
//                    num = 0;
//                    bind.setVisibility(View.INVISIBLE);
//                    remove.setVisibility(View.GONE);
//                    count.setVisibility(View.GONE);
//                } else {
//                    count.setText(num + "");
//                    bind.setBadgeCount(num);
//                }
//            }
//        });
//
//        int[] childCoordinate = new int[2];
//        int[] parentCoordinate = new int[2];
//        int[] shopCoordinate = new int[2];
//        //1.分别获取被点击View、父布局、购物车在屏幕上的坐标xy。
//        v.getLocationInWindow(childCoordinate);
//        rlFather.getLocationInWindow(parentCoordinate);
//        ivCar.getLocationInWindow(shopCoordinate);
//
//        //2.自定义ImageView 继承ImageView
//        CarImageView img = new CarImageView(getContext());
//        img.setImageResource(R.mipmap.apple);
//        //3.设置img在父布局中的坐标位置
//        img.setX(childCoordinate[0] - parentCoordinate[0]);
//        img.setY(childCoordinate[1] - parentCoordinate[1]);
//        //4.父布局添加该Img
//        rlFather.addView(img);
//
//        //5.利用 二次贝塞尔曲线 需首先计算出 MoveImageView的2个数据点和一个控制点
//        PointF startP = new PointF();
//        PointF endP = new PointF();
//        PointF controlP = new PointF();
//        //开始的数据点坐标就是 addV的坐标
//        startP.x = childCoordinate[0] - parentCoordinate[0];
//        startP.y = childCoordinate[1] - parentCoordinate[1];
//        //结束的数据点坐标就是 shopImg的坐标
//        endP.x = shopCoordinate[0] - parentCoordinate[0];
//        endP.y = shopCoordinate[1] - parentCoordinate[1];
//        //控制点坐标 x等于 购物车x；y等于 addV的y
//        controlP.x = endP.x;
//        controlP.y = startP.y;
//
//        //启动属性动画
//        ObjectAnimator animator = ObjectAnimator.ofObject(img, "mPointF",
//                new PointFTypeEvaluator(controlP), startP, endP);
//        animator.setDuration(1000);
//        animator.addListener(this);
//        animator.start();
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//左边条目被点击了
        headListAdapter.setSelectedPosition(i);
        //通过添加数据时右侧添加的
        Head head = headList.get(i);
        shlvGoodsRight.setSelection(head.getGroupFirstIndex());
        isScroll = false;
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        //动画结束后 父布局移除 img
        Object target = ((ObjectAnimator) animator).getTarget();

        rlFather.removeView((View) target);
        //shopImg 开始一个放大动画
        Animation scaleAnim = AnimationUtils.loadAnimation(getContext(), R.anim.shopcar);
        ivCar.startAnimation(scaleAnim);
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        isScroll = true;
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//左边点击  导致右边滚动 只触发这个方法
        if (isScroll) {
            Data data = dataList.get(i);
            //当前正在置顶显示的头
            //左侧通过右侧同组的下标设置选择哪一个item显示
            headListAdapter.setSelectedPosition(data.getHeadIndex());

            //滚动左边时  右边的显示问题
            int firstVisiblePosition = lvGoodsLeft.getFirstVisiblePosition();
            int lastVisiblePosition = lvGoodsLeft.getLastVisiblePosition();
            if (data.getHeadIndex() <= firstVisiblePosition || data.getHeadIndex() >= lastVisiblePosition) {
                lvGoodsLeft.setSelection(data.getHeadIndex());
            }
        }
    }

    @Override
    public void onRefresh() {
        persenter.start();
    }

    @Override
    public void onLoadMore() {

    }

    public class PointFTypeEvaluator implements TypeEvaluator<PointF> {
        /**
         * 每个估值器对应一个属性动画，每个属性动画仅对应唯一一个控制点
         */
        PointF control;
        /**
         * 估值器返回值
         */
        PointF mPointF = new PointF();

        public PointFTypeEvaluator(PointF control) {
            this.control = control;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            return getBezierPoint(startValue, endValue, control, fraction);
        }

        /**
         * 二次贝塞尔曲线公式
         *
         * @param start   开始的数据点
         * @param end     结束的数据点
         * @param control 控制点
         * @param t       float 0-1
         * @return 不同t对应的PointF
         */
        private PointF getBezierPoint(PointF start, PointF end, PointF control, float t) {
            mPointF.x = (1 - t) * (1 - t) * start.x + 2 * t * (1 - t) * control.x + t * t * end.x;
            mPointF.y = (1 - t) * (1 - t) * start.y + 2 * t * (1 - t) * control.y + t * t * end.y;
            return mPointF;
        }
    }
}
