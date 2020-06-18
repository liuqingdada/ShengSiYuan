//Copyright 2012 Mobvoi Inc. All Rights Reserved.
package com.android.xiaomi.app.rootinfo.location;

/**
 * Geo point, it only contains the lat and lng, city
 * 
 * @author Jimmy Chen, <ggm19890303@live.cn>
 */
public class GeoPoint {
    private String city = "";
    private double lat;
    private double lng;
    private static double xPi = Math.PI * 3000.0 / 180.0;
    private static final double ee = 0.00669342162296594323;
    private static final double a = 6378245.0;
    public GeoPoint() {
        this.lat = 0.0;
        this.lng = 0.0;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isValid() {
        if (lat == 0.0 || lng == 0.0) {
            return false;
        } else {
            return true;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static GeoPoint parseFromDouble(double lat, double lng) {
        GeoPoint geopoint = new GeoPoint();
        geopoint.lat = lat;
        geopoint.lng = lng;
        return geopoint;
    }

    public static GeoPoint parseFromString(String lat, String lng) {
        try {
            return parseFromDouble(Double.valueOf(lat), Double.valueOf(lng));
        } catch (Exception e) {
            return parseFromDouble(0.0, 0.0);
        }
    }

    public static GeoPoint parseFromInt(int lat, int lng) {
        return parseFromDouble(lat / 1.0E6, lng / 1.0E6);
    }

    public static GeoPoint parseFromStringInt(String lat, String lng) {
        try {
            return parseFromInt(Integer.valueOf(lat), Integer.valueOf(lng));
        } catch (Exception e) {
            return parseFromDouble(0.0, 0.0);
        }
    }

    /**
     * the geopoint str must layout by lat,lng, else return a invalid geopoint
     */
    public static GeoPoint parseFromStringComma(String geopointStr) {
        try {
            String[] points = geopointStr.split(",");
            if (points.length == 2) {
                return parseFromString(points[0], points[1]);
            }
        } catch (Exception e) {
        }
        return parseFromDouble(0.0, 0.0);
    }

    @Override
    public String toString() {
        return "[lat=" + lat + ", lng=" + lng + "]";
    }

    public String toStringWithComma() {
        return lat + "," + lng;
    }

    public static GeoPoint convertGCJToBaidu(GeoPoint p) {
        if (!p.isValid()) {
            return p;
        }
        double z = Math.sqrt(p.getLng() * p.getLng() + p.getLat() * p.getLat()) + 0.00002 * Math.sin(p.getLat() * xPi);
        double theta = Math.atan2(p.getLat(), p.getLng()) + 0.000003 * Math.cos(p.getLng() * xPi);
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setCity(p.getCity());
        geoPoint.setLat(z * Math.sin(theta) + 0.006);
        geoPoint.setLng(z * Math.cos(theta) + 0.0065);
        return geoPoint;
    }

    public static GeoPoint convertbd092GCJ(GeoPoint p) {
        if (!p.isValid()) {
            return p;
        }
        double x = p.lng - 0.0065;
        double y = p.lat - 0.006;
        double[] latlon = new double[2];
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * xPi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * xPi);
        latlon[0] = z * Math.sin(theta);
        latlon[1] = z * Math.cos(theta);
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setCity(p.getCity());
        geoPoint.setLat(latlon[0]);
        geoPoint.setLng(latlon[1]);
        return geoPoint;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
        return ret;
    }

    // World Geo System ==> Mars Geo System
    public static GeoPoint convertGPSToGCJ(GeoPoint p) {
        if (!p.isValid()) {
            return p;
        }
        double wgLon = p.getLng();
        double wgLat = p.getLat();
        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
        double radLat = wgLat / 180.0 * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.lat = wgLat + dLat;
        geoPoint.lng = wgLon + dLon;
        return geoPoint;
    }

}
