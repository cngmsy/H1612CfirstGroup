package com.jiyun.qcloud.dashixummoban.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */

public class ShopEntity implements Parcelable {

    /**
     * id : 10101
     * info : (不与其它活动同享)13.9元特价套餐!!|13.9特价套餐!!(每单仅限2份)
     * list : [{"bargainPrice":true,"form":"肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101001,"monthSaleNum":53,"name":"肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"肉末烧汁茄子+榄菜肉末四季豆+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101002,"monthSaleNum":37,"name":"肉末烧汁茄子+四季豆套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"手撕杏鲍菇+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101003,"monthSaleNum":27,"name":"手撕杏鲍菇+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"肉末烧汁茄子+杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101004,"monthSaleNum":24,"name":"肉末烧汁茄子+杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"榄菜肉末四季豆+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101005,"monthSaleNum":53,"name":"榄菜肉末四季豆+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"榄菜肉末四季豆+手撕杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101006,"monthSaleNum":53,"name":"榄菜肉末四季豆+手撕杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30}]
     * name : 13.9特价套餐
     */

    private int id;
    private String info;
    private String name;
    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ShopEntity{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }

    public static class ListBean implements Parcelable {
        /**
         * bargainPrice : true
         * form : 肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)
         * icon : http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp
         * id : 101001
         * monthSaleNum : 53
         * name : 肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)
         * new : false
         * newPrice : 13.899999618530273
         * oldPrice : 30
         */

        private boolean bargainPrice;
        private String form;
        private String icon;
        private int id;
        private int monthSaleNum;
        private String name;
        @SerializedName("new")
        private boolean newX;
        private double newPrice;
        private int oldPrice;

        public boolean isBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(boolean bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMonthSaleNum() {
            return monthSaleNum;
        }

        public void setMonthSaleNum(int monthSaleNum) {
            this.monthSaleNum = monthSaleNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
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

        @Override
        public String toString() {
            return "ListBean{" +
                    "bargainPrice=" + bargainPrice +
                    ", form='" + form + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", monthSaleNum=" + monthSaleNum +
                    ", name='" + name + '\'' +
                    ", newX=" + newX +
                    ", newPrice=" + newPrice +
                    ", oldPrice=" + oldPrice +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.bargainPrice ? (byte) 1 : (byte) 0);
            dest.writeString(this.form);
            dest.writeString(this.icon);
            dest.writeInt(this.id);
            dest.writeInt(this.monthSaleNum);
            dest.writeString(this.name);
            dest.writeByte(this.newX ? (byte) 1 : (byte) 0);
            dest.writeDouble(this.newPrice);
            dest.writeInt(this.oldPrice);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.bargainPrice = in.readByte() != 0;
            this.form = in.readString();
            this.icon = in.readString();
            this.id = in.readInt();
            this.monthSaleNum = in.readInt();
            this.name = in.readString();
            this.newX = in.readByte() != 0;
            this.newPrice = in.readDouble();
            this.oldPrice = in.readInt();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.info);
        dest.writeString(this.name);
        dest.writeTypedList(this.list);
    }

    public ShopEntity() {
    }

    protected ShopEntity(Parcel in) {
        this.id = in.readInt();
        this.info = in.readString();
        this.name = in.readString();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<ShopEntity> CREATOR = new Parcelable.Creator<ShopEntity>() {
        @Override
        public ShopEntity createFromParcel(Parcel source) {
            return new ShopEntity(source);
        }

        @Override
        public ShopEntity[] newArray(int size) {
            return new ShopEntity[size];
        }
    };
}
