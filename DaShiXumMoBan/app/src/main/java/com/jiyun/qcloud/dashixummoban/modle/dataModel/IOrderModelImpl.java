package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public class IOrderModelImpl implements IHomeModel {
    @Override
    public void loadHomeList(NetWorkCallBack<BaseBean> callback) {
<<<<<<< HEAD
<<<<<<< HEAD
        iHttp.get(Urls.,null,callback);
=======
       // iHttp.get(Urls.ORDER,null,callback);
>>>>>>> 9fb2c3c2520c53f5525a3b1a2578c2dc897a6060
=======
        iHttp.get(Urls.ORDER,null,callback);
>>>>>>> 7de4a4e02b1f00ef936d8e3bcbe7ee7c9bde747a
    }
}
