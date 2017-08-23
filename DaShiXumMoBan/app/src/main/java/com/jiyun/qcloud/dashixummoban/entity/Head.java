package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by admin on 2017/8/16.
 */
//左边标题的Bean
public class Head {
    private String info;   //标题内容
    private int groupFirstIndex;   //点击(左边的)某个头时,需要知道其分组容器(右边)中对应组元素中第一条数据的下标

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getGroupFirstIndex() {
        return groupFirstIndex;
    }

    public void setGroupFirstIndex(int groupFirstIndex) {
        this.groupFirstIndex = groupFirstIndex;
    }
}
