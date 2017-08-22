package com.jiyun.qcloud.dashixummoban.app;

import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.mob.MobSDK;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by chj on 2017/8/20.
 */

public class App extends  BaseApplication implements Thread.UncaughtExceptionHandler{

    public static BaseActivity mBaseActivity;
    public static BaseFragment lastfragment;


    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this, "1fa51968109fd", "89ca627b6e85ef537015b94b5eab778c");
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.setDefaultUncaughtExceptionHandler(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
    }

}
