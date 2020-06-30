package com.android.xiaomi.app.rootinfo.location;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.common.utils.LogUtil;

public class LocationPoint {
    private static final String TAG = "LocationPoint";

    /**
     * 纬度
     */
    public double latitude;
    /**
     * 经度
     */
    public double longitude;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LocationPoint)) {
            return false;
        }
        LocationPoint other = (LocationPoint) obj;
        return this.latitude == other.latitude && this.longitude == other.longitude;
    }

    @Override
    public String toString() {
        return "Point[latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    public static LocationPoint from(double latitude, double longitude) {
        LocationPoint point = new LocationPoint();
        point.latitude = latitude;
        point.longitude = longitude;
        return point;
    }

    /**
     * 解析位置信息，支持的格式："31.308912471391192,121.49982640557423"
     */
    @Nullable
    public static LocationPoint parseLocationPoint(String location) {
        if (TextUtils.isEmpty(location)) {
            return null;
        }
        try {
            String[] fields = location.split(",");
            if (fields.length == 2) {
                LocationPoint point = new LocationPoint();
                point.latitude = Double.parseDouble(fields[0]);
                point.longitude = Double.parseDouble(fields[1]);
                return point;
            }
        } catch (Exception e) {
            LogUtil.w(TAG, "failed to parse location point [" + location + "]", e);
        }
        return null;
    }

    /**
     * 把火星坐标系/国测局经纬度坐标系（gcj02）转换为百度坐标系（bd09）
     */
    @NonNull
    public static LocationPoint convertGCJToBaidu(@NonNull LocationPoint point) {
        GeoPoint gcjPoint = GeoPoint.parseFromDouble(point.latitude, point.longitude);
        GeoPoint bdPoint = GeoPoint.convertGCJToBaidu(gcjPoint);
        return LocationPoint.from(bdPoint.getLat(), bdPoint.getLng());
    }

    public static LocationPoint convertBaiduToGCJ(@NonNull LocationPoint point) {
        GeoPoint bdPoint = GeoPoint.parseFromDouble(point.latitude, point.longitude);
        GeoPoint gcjPoint = GeoPoint.convertbd092GCJ(bdPoint);
        return LocationPoint.from(gcjPoint.getLat(), gcjPoint.getLng());
    }

    /**
     * 转换为服务器需要的",,,,,,39.99855111136873,116.34438004753852" 格式
     */
    public String toServerAddressString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(",,,,,,");
        buffer.append(latitude);
        buffer.append(",");
        buffer.append(longitude);
        return buffer.toString();
    }
}
