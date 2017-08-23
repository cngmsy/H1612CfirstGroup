package com.jiyun.qcloud.dashixummoban.ui.more;

import com.jiyun.qcloud.dashixummoban.modle.more1.IMoreDel;
import com.jiyun.qcloud.dashixummoban.modle.more1.IMoreModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;

/**
 * Created by lenovo on 2017/8/22.
 */

public class MorePresent implements MoreContrat.Presenter {
    private MoreContrat.View movieview;
    private IMoreDel moreDel;

    public MorePresent(MoreContrat.View movieview) {
        this.movieview = movieview;
        movieview.setPresenter(this);
        this.moreDel = new IMoreModelImpl();
    }

    @Override
    public void start() {
        movieview.showProgress();
        moreDel.loadmovielist(new NetWorkCallBack<Radio>() {
            @Override
            public void onSuccess(Radio radio) {
                movieview.showMoviedata(radio);
                movieview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
