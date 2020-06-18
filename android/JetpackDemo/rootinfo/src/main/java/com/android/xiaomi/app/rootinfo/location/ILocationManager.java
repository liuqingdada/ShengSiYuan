package com.android.xiaomi.app.rootinfo.location;

import androidx.annotation.NonNull;


/**
 * Created by shbliu on 2017/3/29.
 * ILocationManager
 */
public interface ILocationManager {
    void init();

    LocationInfo getLocation();

    void startLocation();

    void stopLocation();

    void destroy();

    void addCallback(@NonNull LocationCallback locationCallback);

    void removeCallback(LocationCallback locationCallback);

    interface LocationCallback {
        void onLocationSuccess(@NonNull LocationInfo locationInfo);

        void onLocationFail(int errorCode);
    }
}
