package com.android.lib.uicommon.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.android.common.utils.LogUtil;

import java.util.List;

/**
 * Created by andy
 * 2019-10-21.
 * Email: 1239604859@qq.com
 */
public class PackageUtils {
    private static final String TAG = "PackageUtils";

    public static String getLauncherClassName(Context context, String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return null;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        for (ResolveInfo resolveInfo : resolveinfoList) {
            LogUtil.d(TAG, "resolveInfo:" + resolveInfo);
        }
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            return resolveinfo.activityInfo.name;
        }
        return null;
    }

    public static void startComponent(Context context, String packageName, String className) {
        ComponentName activity = new ComponentName(packageName, className);
        Intent intent = new Intent();
        intent.setComponent(activity);
        startSafely(context, intent);
    }

    private static void startSafely(Context context, Intent intent) {
        if (context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            LogUtil.e(TAG, "Intent is not available! " + intent);
        }
    }
}
