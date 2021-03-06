package com.android.common.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;

public class ApplicationUtils {
    private static final String TAG = "ApplicationUtils";

    @SuppressLint("StaticFieldLeak")
    private static Application sApp;
    private static Handler sHandler;

    private static String sAppVersionName;
    private static int sAppVersionCode;
    private static String sProcessName;

    public static void init(Application app) {
        sApp = app;
        sHandler = new Handler();
    }

    public static Application getApplication() {
        Preconditions.checkNotNull(sApp);
        return sApp;
    }

    /**
     * Get a global handler for the main thread.
     */
    public static Handler getHandler() {
        Preconditions.checkNotNull(sHandler);
        return sHandler;
    }

    public static String getAppVersionName() {
        Preconditions.checkNotNull(sApp);
        return getAppVersionName(sApp);
    }

    public static String getAppVersionName(Context cxt) {
        if (TextUtils.isEmpty(sAppVersionName)) {
            loadAppVersionInfo(cxt);
        }
        return sAppVersionName;
    }

    public static int getAppVersionCode() {
        Preconditions.checkNotNull(sApp);
        return getAppVersionCode(sApp);
    }

    public static int getAppVersionCode(Context cxt) {
        if (TextUtils.isEmpty(sAppVersionName)) {
            loadAppVersionInfo(cxt);
        }
        return sAppVersionCode;
    }

    public static long getAppVersionCode(@NonNull Context context, @NonNull String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "getAppVersionCode", e);
        }
        return 0;
    }

    public static String getAppName(@NonNull Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo
                    applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "getAppName", e);
            return "";
        }
    }

    private static void loadAppVersionInfo(Context cxt) {
        try {
            PackageManager pm = cxt.getPackageManager();
            PackageInfo pkgInfo = pm.getPackageInfo(cxt.getPackageName(), 0);
            sAppVersionName = pkgInfo.versionName;
            sAppVersionCode = pkgInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "cannot find out myself");
        }
    }
}
