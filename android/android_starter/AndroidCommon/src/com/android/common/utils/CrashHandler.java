package com.android.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Process;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 应用崩溃信息保存
 * 保存文件路径: {@link LogUtil.Dir#LOG_DIR}
 * 文件格式: "crash_log_" + time + ".log"
 * 文件内容: 设备信息, StackTrace of Throwable
 */
public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";

    private UncaughtExceptionHandler mDefaultHandler;

    @SuppressLint("StaticFieldLeak")
    private static CrashHandler sInstance;

    private Context mContext;

    private final Map<String, String> mDeviceInfo = new HashMap<>();

    private final DateFormat mFormatter = new SimpleDateFormat("yyMMdd", Locale.getDefault());
    private final DateFormat mCurrentTimeFormatter =
            new SimpleDateFormat("yy-MM-dd HH:mm:ss:SSS", Locale.getDefault());

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (sInstance == null) {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NotNull Thread thread, @NotNull Throwable tx) {
        handleException(tx);
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, tx);
        }
    }

    public void handleException(Throwable tx) {
        if (tx == null) {
            return;
        }
        LogUtil.d(TAG, "crash happens", tx);
        collectDeviceInfo(mContext);
        saveCrashInfoToFile(tx);
    }

    private void collectDeviceInfo(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES
            );
            if (info != null) {
                String versionName = info.versionName == null ? "null" : info.versionName;
                String versionCode = info.versionCode + "";
                mDeviceInfo.put("versionName", versionName);
                mDeviceInfo.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            // ignore
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceInfo.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private void saveCrashInfoToFile(Throwable tx) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : mDeviceInfo.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        sb.append(mCurrentTimeFormatter.format(System.currentTimeMillis()));
        sb.append("\npid=").append(Process.myPid());
        sb.append("\n");

        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        tx.printStackTrace(pw);
        Throwable cause = tx.getCause();
        while (cause != null) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();
        String result = writer.toString();
        sb.append(result);
        sb.append("\n\n");

        try {
            String time = mFormatter.format(new Date());
            String fileName = "crash_log_" + time + ".txt";
            String path = LogUtil.Dir.LOG_DIR;
            if (path != null) {
                File dir = new File(path);
                if (!dir.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(new File(path, fileName), true);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
        } catch (Exception ex) {
            // ignore
        }
    }
}
