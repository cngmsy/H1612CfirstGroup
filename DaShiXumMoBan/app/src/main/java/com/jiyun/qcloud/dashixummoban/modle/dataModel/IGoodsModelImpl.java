package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by admin on 2017/8/22.
 */

public class IGoodsModelImpl implements IHomeModel{

    @Override
    public void loadHomeList(NetWorkCallBack<BaseBean> callback) {
        iHttp.get(Urls.SHOPPINGCAR,null,callback);
    }
}
