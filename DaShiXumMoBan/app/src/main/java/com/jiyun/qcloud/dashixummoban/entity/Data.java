package com.jiyun.qcloud.dashixummoban.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/8/16.
 */
//右边的Bean
public class Data implements Parcelable {
    //普通条目的内容
    private String pic;
    private String title;
    private String content;
    private String sells;
    private double newPrice;
    private int oldPrice;
    private int count;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public static Creator<Data> getCREATOR() {
        return CREATOR;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int headId;  //进行分组操作,同组数据该字段相同
    private int headIndex;  //当前条目对应的头数据所在集合的index下标

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSells() {
        return sells;
    }

    public void setSells(String sells) {
        this.sells = sells;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getHeadIndex() {
        return headIndex;
    }

    public void setHeadIndex(int headIndex) {
        this.headIndex = headIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pic);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.sells);
        dest.writeDouble(this.newPrice);
        dest.writeInt(this.oldPrice);
        dest.writeInt(this.count);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.headId);
        dest.writeInt(this.headIndex);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.pic = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.sells = in.readString();
        this.newPrice = in.readDouble();
        this.oldPrice = in.readInt();
        this.count = in.readInt();
        this.isChecked = in.readByte() != 0;
        this.headId = in.readInt();
        this.headIndex = in.readInt();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
