package com.jiyun.qcloud.dashixummoban.utils;

import android.os.Parcel;
import android.os.Parcelable;


import com.jiyun.qcloud.dashixummoban.entity.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/8/14.
 */

public class ParcelableMap implements Parcelable {
    private Map<Data,Integer> map;
    public Map<Data,Integer> getMap()
    {
        return map;
    }
    public void setMap(Map<Data,Integer> map)
    {
        this.map=map;
    }

    @Override
    public String toString() {
        return "ParcelableMap{" +
                "map=" + map +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.map.size());
        for (Map.Entry<Data, Integer> entry : this.map.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeValue(entry.getValue());
        }
    }

    public ParcelableMap() {
    }

    protected ParcelableMap(Parcel in) {
        int mapSize = in.readInt();
        this.map = new HashMap<Data, Integer>(mapSize);
        for (int i = 0; i < mapSize; i++) {
            Data key = in.readParcelable(Data.class.getClassLoader());
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.map.put(key, value);
        }
    }

    public static final Parcelable.Creator<ParcelableMap> CREATOR = new Parcelable.Creator<ParcelableMap>() {
        @Override
        public ParcelableMap createFromParcel(Parcel source) {
            return new ParcelableMap(source);
        }

        @Override
        public ParcelableMap[] newArray(int size) {
            return new ParcelableMap[size];
        }
    };
}
