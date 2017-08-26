package com.jiyun.qcloud.dashixummoban.main;

import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.manager.ActivityCollector;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;
import com.jiyun.qcloud.dashixummoban.ui.home1.HomeFragment;
import com.jiyun.qcloud.dashixummoban.ui.home1.HomePresenter;
import com.jiyun.qcloud.dashixummoban.ui.more.MoreFragment;
import com.jiyun.qcloud.dashixummoban.ui.more.MorePresent;
import com.jiyun.qcloud.dashixummoban.ui.order.OrderFragment;
import com.jiyun.qcloud.dashixummoban.ui.order.OrderPresenter;
import com.jiyun.qcloud.dashixummoban.ui.person.PersonalFragment;

import butterknife.BindView;
import butterknife.OnClick;
import util.UpdateAppUtils;

/**
 * Created by chj on 2017/8/20.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.but1)
    RadioButton but1;
    @BindView(R.id.but2)
    RadioButton but2;
    @BindView(R.id.but4)
    RadioButton but3;
    @BindView(R.id.but5)
    RadioButton but4;
    @BindView(R.id.linear)
    RadioGroup linear;
    private FragmentManager fragmentManager;
    private long mExitTime;
    private String address;
    @Override
    protected void initData() {
        UpdateAppUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath("http://123.206.14.104:8080/FileUploadDemo/files/kk.apk")
                .update();
        address = getIntent().getStringExtra("address");
        fragmentManager = App.mBaseActivity.getSupportFragmentManager();
        HomeFragment homeFragment = (HomeFragment) FragmentMager.getInstance().start(R.id.fragment, HomeFragment.class, false).build();
        new HomePresenter(homeFragment);
        but1.setChecked(true);
    }

    @Override
    protected void initView() {

    }
    public String send() {
        if (address != null) {
            return address;
        } else {
            return null;
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.but1, R.id.but2, R.id.but4, R.id.but5, R.id.linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but1:
                FragmentMager.getInstance().start(R.id.fragment, HomeFragment.class, false).build();
                break;
            case R.id.but2:
<<<<<<< HEAD
                OrderFragment build = (OrderFragment) FragmentMager.getInstance().start(R.id.fragment, OrderFragment.class, false).build();
                new OrderPresenter(build);
=======
                OrderFragment order = (OrderFragment) FragmentMager.getInstance().start(R.id.fragment, OrderFragment.class, false).build();
                new OrderPresenter(order);
>>>>>>> 94058744c75970f1a5716030c7e3d63093d8bc9b
                break;
            case R.id.but4:
              FragmentMager.getInstance().start(R.id.fragment, PersonalFragment.class,false).build();
                break;
            case R.id.but5:
                MoreFragment fragment = (MoreFragment) FragmentMager.getInstance().start(R.id.fragment, MoreFragment.class, false).build();
                new MorePresent(fragment);
                break;
            case R.id.linear:
                break;
        }
    }

    ///////////////////

    /**
     * 自定义回退栈管理；
     * 获取栈顶的fragment的名字，判断名字是否和主页的名字是否一样，
     * 如果一样就退出应用，如果不是就回退上一个fragment；
     * <p>
     * onBackPressed()与onKeyDown
     */
    @Override
    public void onBackPressed() {
        String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HomePageFragment".equals(simpleName) ||
                "LivePageFragment".equals(simpleName) ||
                "MyFragment".equals(simpleName) ||
                "MoreFragment".equals(simpleName)
                ) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();//
                String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                App.lastfragment = (BaseFragment) fragmentManager.findFragmentByTag(name);
            }
        }
    }

    /**
     * 双击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HomePageFragment".equals(name) ||
                "LivePageFragment".equals(name) ||
                "MyFragment".equals(name) ||
                "MoreFragment".equals(name)
                ) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {//back键被按下了
                if ((System.currentTimeMillis() - mExitTime) > 2000) {//第二次点击判断是否在两秒内完成，是的话Finish掉（退出）
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    ActivityCollector.getInstance().exit(App.mBaseActivity);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}




