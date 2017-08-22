package com.jiyun.qcloud.dashixummoban.ui.home1;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class HomeContract {
    public interface View extends IBaseView<Presenter> {
        void showHomeListData(BaseBean baseBean);
        void playVideo();
        void loadWebView();
    }

    public interface Presenter extends IBasePresenter {
    }
}
