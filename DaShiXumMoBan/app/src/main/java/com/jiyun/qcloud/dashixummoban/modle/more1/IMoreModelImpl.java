package com.jiyun.qcloud.dashixummoban.modle.more1;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.more.bean.Radio;

/**
 * Created by lenovo on 2017/8/22.
 */

public class IMoreModelImpl implements IMoreDel{

    @Override
    public void loadmovielist(NetWorkCallBack<Radio> callback) {
        iHttp.get(Urls.MORE,null,callback);
    }
}
