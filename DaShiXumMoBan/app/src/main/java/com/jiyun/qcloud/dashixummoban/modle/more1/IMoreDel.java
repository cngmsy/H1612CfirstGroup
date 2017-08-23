package com.jiyun.qcloud.dashixummoban.modle.more1;

import com.jiyun.qcloud.dashixummoban.base.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;

/**
 * Created by lenovo on 2017/8/22.
 */

public interface IMoreDel extends BaseModel{
    void loadmovielist(NetWorkCallBack<Radio> callback);}
