package com.jiyun.qcloud.dashixummoban.ui.person;

/**
 * Created by Administrator on 2017/8/25.
 */
public class HeadResult {
    private String result;
    private String url;

    public HeadResult(String result, String url) {
        this.result = result;
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
