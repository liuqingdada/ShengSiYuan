package com.android.cooper.app.workmanager.log;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.android.app.common.utils.ApplicationUtils;
import com.android.app.common.utils.FileLogger;
import com.android.app.common.utils.LogUtil;

import java.io.File;
import java.util.Arrays;

/**
 * Created by cooper
 * 20-6-8.
 * Email: 1239604859@qq.com
 * 如果要用 Timber, 可以在这里实现 Timber.Tree
 */
public class LogUtilTree {
    private static final String LOG_FILE_PREFIX = "tvassistant";
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
        AsyncTask.SERIAL_EXECUTOR.execute(() -> {
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
            Context context = ApplicationUtils.getApplication();
            File logDir = new File(context.getExternalFilesDir(null), "tvassistant/log");
            return logDir.getAbsolutePath();
        } else {
            return null;
        }
    }
}
