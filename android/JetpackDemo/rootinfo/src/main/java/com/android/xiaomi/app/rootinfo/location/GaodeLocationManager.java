package com.android.xiaomi.app.rootinfo.location;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.app.common.utils.ApplicationUtils;
import com.android.app.common.utils.LogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by shbliu on 2017/3/28.
 */
class GaodeLocationManager implements ILocationManager, AMapLocationListener, Handler.Callback {
    private static final String TAG = "GDLocationManager";
    //默认2分钟定位间隔,之后可调整
    private static final long LOCATION_INTERNAL = 1000 * 60 * 2;
    private static final int MSG_START_LOCATION = 1;
    private static final int GD_ERROR_CODE_OK = 0;

    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mAMapLocationClientOption;

    private LocationInfo mLocationInfo;

    private Handler mHandler;

    //正在定位中
    private volatile boolean mIsLocating;

    private Gson mGson;

    private ArrayList<ILocationManager.LocationCallback> mLocationCallbacks;

    @Override
    public void addCallback(@NonNull ILocationManager.LocationCallback locationCallback) {
        if (!mLocationCallbacks.contains(locationCallback)) {
            mLocationCallbacks.add(locationCallback);
        }
    }

    @Override
    public void removeCallback(ILocationManager.LocationCallback locationCallback) {
        mLocationCallbacks.remove(locationCallback);
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption option = new AMapLocationClientOption();
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置gps优先后，如果30秒内GPS没有返回定位结果则进行网络定位，不论是否开启gps，在室内Gps信号不好时，
        // 回调结果的时间都会变为30s，暂时没有修改
        option.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        option.setHttpTimeOut(20000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        option.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        option.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        AMapLocationClientOption.setLocationProtocol(
                AMapLocationClientOption.AMapLocationProtocol.HTTP);
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        option.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，
        // 定位位置可能存在误差
        option.setWifiScan(true);
        option.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        return option;
    }

    @Override
    public void init() {
        //初始化client
        mLocationClient = new AMapLocationClient(ApplicationUtils.getApplication());
        mAMapLocationClientOption = getDefaultOption();
        //设置定位参数
        mLocationClient.setLocationOption(mAMapLocationClientOption);
        // 设置定位监听
        mLocationClient.setLocationListener(this);

        mHandler = new Handler(this);
        mGson = new Gson();
        mLocationCallbacks = new ArrayList<>();
    }

    @Override
    @Nullable
    public LocationInfo getLocation() {

        if (mLocationInfo != null) {
            return mLocationInfo;
        }
        //进入前台时，开始定位，应该不需要保存时间
        String location = AppPreferenceHelper.getLocationInfo();

        //第一次或清除数据后没有得到数据时，会返回null
        return mGson.fromJson(location, LocationInfo.class);
    }

    //AMapLocationClient在主线程或者Looper.myLooper为null的线程创建时，此方法在主线程回调，
    // 否则，此方法在创建时线程回调
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        mHandler.sendEmptyMessageDelayed(MSG_START_LOCATION, LOCATION_INTERNAL);
        mIsLocating = false;
        if (aMapLocation != null && aMapLocation.getErrorCode() == GD_ERROR_CODE_OK) {
            LocationInfo locationInfo = new LocationInfo();
            //高德地图使用gcj-2坐标
            LocationPoint gcjPoint = LocationPoint.from(
                    aMapLocation.getLatitude(),
                    aMapLocation.getLongitude()
            );
            locationInfo.point = LocationPoint.convertGCJToBaidu(gcjPoint);
            locationInfo.country = aMapLocation.getCountry();
            locationInfo.province = aMapLocation.getProvince();
            locationInfo.city = aMapLocation.getCity();
            locationInfo.district = aMapLocation.getDistrict();
            locationInfo.street = aMapLocation.getStreet();
            locationInfo.streetNumber = aMapLocation.getStreetNum();

            mLocationInfo = locationInfo;

            String location = mGson.toJson(locationInfo, LocationInfo.class);

            AppPreferenceHelper.setLocationInfo(location);
            LogUtil.d(TAG, aMapLocation.toStr());
            LogUtil.d(TAG, location);

            for (ILocationManager.LocationCallback callback : mLocationCallbacks) {
                callback.onLocationSuccess(locationInfo);
            }
        } else {
            int errorCode = Integer.MIN_VALUE;
            if (aMapLocation == null) {
                LogUtil.d(TAG, "aMapLocation is null");
            } else {
                LogUtil.d(TAG, "errorcode: %1$d errorinfo:%2$s locationdetail:%3$s",
                        aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(),
                        aMapLocation.getLocationDetail()
                );
                errorCode = aMapLocation.getErrorCode();
            }
            for (ILocationManager.LocationCallback callback : mLocationCallbacks) {
                callback.onLocationFail(errorCode);
            }
        }
    }

    @Override
    public void startLocation() {
        //目前只在主线程调用，确保同一时间只进行一次定位
        if (mIsLocating) {
            return;
        }
        mHandler.removeMessages(MSG_START_LOCATION);
        mLocationClient.startLocation();
        mIsLocating = true;
    }

    @Override
    public void stopLocation() {
        mHandler.removeMessages(MSG_START_LOCATION);
        mLocationClient.stopLocation();
        mIsLocating = false;
    }

    //清理内存占用
    @Override
    public void destroy() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            mAMapLocationClientOption = null;
        }
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        mGson = null;
        mLocationCallbacks = null;
        mIsLocating = false;
        mLocationInfo = null;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == MSG_START_LOCATION) {
            mLocationClient.startLocation();
            return true;
        }
        return false;
    }
}
