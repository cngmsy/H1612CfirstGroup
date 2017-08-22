package com.jiyun.qcloud.dashixummoban.ui.order;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class OrderContract {
    interface View extends IBaseView<Presenter> {
        void showHomeListData(BaseBean baseBean);
        void playVideo();
        void loadWebView();

    }

    interface Presenter extends IBasePresenter {
    }
}
