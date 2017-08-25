package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class IOrderModelImpl implements IHomeModel {
    @Override
    public void loadHomeList(NetWorkCallBack<BaseBean> callback) {
       // iHttp.get(Urls.ORDER,null,callback);
    }
}
