package com.jiyun.qcloud.dashixummoban.ui.home1;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IHomeModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View homeview;
    private IHomeModel homeModel;

    public HomePresenter(HomeContract.View homeview) {
        this.homeview = homeview;
        homeview.setPresenter(this);
        this.homeModel = new IHomeModelImpl();
    }

    @Override
    public void start() {
        homeview.showProgress();
        homeModel.loadHomeList(new NetWorkCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                homeview.showHomeListData(baseBean);
                homeview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeview.showMessage(errorMsg);
                homeview.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
