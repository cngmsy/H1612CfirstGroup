package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.base.BaseBean;
import com.jiyun.qcloud.dashixummoban.base.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by 82年的笔记本 on 2017/8/21.
 */

public interface IHomeModel extends BaseModel {
    void loadHomeList(NetWorkCallBack<BaseBean> callback);
}
