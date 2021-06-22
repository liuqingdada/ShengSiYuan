package com.android.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(final Activity activity, final float dpValue) {
        if (activity != null) {
            final DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            final float scale = metrics.density;
            return (int) ((dpValue * scale) + 0.5f);
        }

        return 0;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(final Context context, final float dpValue) {
        if (context != null) {
            final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            final float scale = metrics.density;
            return (int) ((dpValue * scale) + 0.5f);
        }

        return 0;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(final Activity activity, final float pxValue) {
        if (activity != null) {
            final DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            final float scale = metrics.density;
            return (int) ((pxValue / scale) + 0.5f);
        }

        return 0;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(final Context context, final float pxValue) {
        if (context != null) {
            final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            final float scale = metrics.density;
            return (int) ((pxValue / scale) + 0.5f);
        }

        return 0;
    }

    public static int getScreenWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * @return 是否是全面屏
     */
    public static boolean isAllScreenDevice(Context context) {
        boolean mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }
}
