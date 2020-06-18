package com.android.xiaomi.app.rootinfo.location;

public class LocationInfo {
    /** 经纬度坐标（百度坐标系：bd09）*/
    public LocationPoint point;
    public String country;
    public String province;
    public String city;
    public String district;
    public String street;
    public String streetNumber;
    public long time;

    @Override
    public String toString() {
        return "LocationInfo[point=" + point +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", street=" + street +
                ", streetNumber=" + streetNumber +
                "]";
    }
}