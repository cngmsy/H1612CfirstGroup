package com.jiyun.qcloud.dashixummoban.ui.order;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IOrderModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class OrderPresenter implements OrderContract.Presenter {
    private OrderContract.View orderview;
    private IHomeModel homeModel;

    public OrderPresenter(OrderContract.View orderview) {
        this.orderview = orderview;
        orderview.setPresenter(this);
        this.homeModel = new IOrderModelImpl();
    }

    @Override
    public void start() {
        orderview.showProgress();
        homeModel.loadHomeList(new NetWorkCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                orderview.showHomeListData(baseBean);
                orderview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                orderview.showMessage(errorMsg);
                orderview.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
