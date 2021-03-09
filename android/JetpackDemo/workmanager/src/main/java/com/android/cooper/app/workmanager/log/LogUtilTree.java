package com.android.cooper.app.workmanager.log;

import android.content.Context;
import android.os.Environment;

import com.android.common.utils.ApplicationUtils;
import com.android.common.utils.ExecutorsKt;
import com.android.common.utils.FileLogger;
import com.android.common.utils.LogUtil;
import com.android.cooper.app.workmanager.BuildConfig;

import java.io.File;
import java.util.Arrays;

/**
 * Created by cooper
 * 20-6-8.
 * Email: 1239604859@qq.com
 * 如果要用 Timber, 可以在这里实现 Timber.Tree
 */
public class LogUtilTree {
    private static final String LOG_FILE_PREFIX = "workmanager";
    private static final int MAX_LOG_FILE_COUNT = 20;
    public static final String LOG_DIR;

    static {
        LOG_DIR = initLogDir();
    }

    public LogUtilTree(boolean debug, String processNameSuffix) {
        LogUtil.setDebug(debug);
        LogUtil.setFileLogger(new FileLogger(LOG_DIR, LOG_FILE_PREFIX, processNameSuffix));
    }

    public void init() {
        ExecutorsKt.serialExecute(() -> {
            if (LOG_DIR == null) {
                return;
            }
            File logDir = new File(LOG_DIR);
            File[] logFiles =
                    logDir.listFiles((dir, filename) -> filename.startsWith(LOG_FILE_PREFIX));

            if (logFiles != null && logFiles.length > MAX_LOG_FILE_COUNT) {
                Arrays.sort(
                        logFiles,
                        (lhs, rhs) -> Long.compare(rhs.lastModified(), lhs.lastModified())
                );
                int size = logFiles.length;
                for (int i = MAX_LOG_FILE_COUNT; i < size; i++) {
                    logFiles[i].delete();
                }
            }
        });
    }

    private static String initLogDir() {
        boolean mounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (mounted) {
            Context context = ApplicationUtils.getApplication().getApplicationContext();
            File logDir = new File(context.getExternalFilesDir(null), "workmanager/log");
            return logDir.getAbsolutePath();
        } else {
            return null;
        }
    }

    public static void main(String... args) {
        Context context = ApplicationUtils.getApplication().getApplicationContext();
        String processName = ProcessUtil.getCurrentProcessName(context);
        String processNameSuffix = ProcessUtil.getCurrentProcessNameSuffix(context);
        boolean isMainpProcess = ProcessUtil.isMainProcess(context);
        if (isMainpProcess) {
            new LogUtilTree(BuildConfig.DEBUG, processNameSuffix).init();
        }
        CrashHandler.getInstance().init(context);
    }
}
