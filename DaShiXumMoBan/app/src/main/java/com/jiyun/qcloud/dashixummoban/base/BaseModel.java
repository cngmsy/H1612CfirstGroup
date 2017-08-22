package com.jiyun.qcloud.dashixummoban.base;


import com.jiyun.qcloud.dashixummoban.modle.net.HttpFactory;

/**
 * Created by xingge on 2017/7/11.
 */

public interface BaseModel {
    public static IBaseHttp iHttp = HttpFactory.createOK();
}
