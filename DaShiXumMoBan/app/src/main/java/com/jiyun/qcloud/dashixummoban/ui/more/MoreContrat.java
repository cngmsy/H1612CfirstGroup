package com.jiyun.qcloud.dashixummoban.ui.more;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;

/**
 * Created by lenovo on 2017/8/22.
 */

public class MoreContrat {
    public interface View extends IBaseView<Presenter> {
        void showMoviedata(Radio movieBean);

    }

    interface Presenter extends IBasePresenter {}
}
