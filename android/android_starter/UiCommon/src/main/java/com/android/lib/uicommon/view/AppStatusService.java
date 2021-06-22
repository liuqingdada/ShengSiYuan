package com.android.lib.uicommon.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.android.common.utils.LogUtil;

/**
 * Created by andy
 * 2019/2/16.
 * Email: 1239604859@qq.com
 */
public class AppStatusService {
    private static final String TAG = "AppStatusService";
    private static int activityCounter = 0;
    private Class<? extends Activity> mTopActivityClazz;

    public static AppStatusService getInstance() {
        return Holder.instance;
    }

    public void onCreate(Application app) {
        ActivityLifecycleCallbacksImpl activityLifecycleCallbacksImpl =
                new ActivityLifecycleCallbacksImpl();
        app.registerActivityLifecycleCallbacks(activityLifecycleCallbacksImpl);
    }

    // 判断App是否在后台运行
    public boolean isAppRunningBackground() {
        boolean flag = false;
        if (activityCounter == 0) {
            flag = true;
        }
        return flag;
    }

    public Class<? extends Activity> getTopActivityClazz() {
        return mTopActivityClazz;
    }

    private void onEnterBackground() {
        LogUtil.i(TAG, "onEnterBackground: ");
    }

    private void onEnterForeground() {
        LogUtil.i(TAG, "onEnterForeground: ");
    }

    private class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (activityCounter == 0) {
                onEnterForeground();
            }
            activityCounter++;
            mTopActivityClazz = activity.getClass();
            LogUtil.d(
                    TAG,
                    "onActivityStarted: " + activity.getClass()
                            .getName() + ", activityCounter = " + activityCounter
            );
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            activityCounter--;
            LogUtil.d(
                    TAG,
                    "onActivityStopped: " + activity.getClass()
                            .getName() + ", activityCounter = " + activityCounter
            );

            if (activityCounter == 0) {
                onEnterBackground();
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }

    static class Holder {
        private static final AppStatusService instance = new AppStatusService();
    }
}
