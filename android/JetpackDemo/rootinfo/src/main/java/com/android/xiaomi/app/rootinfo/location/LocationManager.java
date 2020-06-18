package com.android.xiaomi.app.rootinfo.location;

import androidx.annotation.NonNull;

/**
 * Created by shbliu on 2017/3/29.
 * LocationManager
 */

public class LocationManager implements ILocationManager {
    private GaodeLocationManager mGaodeLocationManager;
    private boolean mManagerInited;

    private static volatile LocationManager sInstance;

    public static LocationManager getInstance() {
        if (sInstance == null) {
            synchronized (LocationManager.class) {
                if (sInstance == null) {
                    sInstance = new LocationManager();
                }
            }
        }
        return sInstance;
    }

    private LocationManager() {
    }

    @Override
    public void init() {
        if (mManagerInited) {
            throw new RuntimeException("LocationManager already inited");
        }
        mGaodeLocationManager = new GaodeLocationManager();
        mGaodeLocationManager.init();
        mManagerInited = true;
    }

    @Override
    public LocationInfo getLocation() {
        checkManagerInited();
        return mGaodeLocationManager.getLocation();
    }

    @Override
    public void startLocation() {
        checkManagerInited();
        mGaodeLocationManager.startLocation();
    }

    @Override
    public void stopLocation() {
        checkManagerInited();
        mGaodeLocationManager.stopLocation();
    }

    @Override
    public void destroy() {
        checkManagerInited();
        mGaodeLocationManager.destroy();
        mGaodeLocationManager = null;
        mManagerInited = false;
    }

    /**
     * LocationCallback在主线程中回调
     */
    @Override
    public void addCallback(@NonNull ILocationManager.LocationCallback locationCallback) {
        checkManagerInited();
        mGaodeLocationManager.addCallback(locationCallback);
    }

    @Override
    public void removeCallback(ILocationManager.LocationCallback locationCallback) {
        checkManagerInited();
        mGaodeLocationManager.removeCallback(locationCallback);
    }

    private void checkManagerInited() {
        if (!mManagerInited) {
            throw new RuntimeException("LocationManager not inited yet");
        }
    }
}
