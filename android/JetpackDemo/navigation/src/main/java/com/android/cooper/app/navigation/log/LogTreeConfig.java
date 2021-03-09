package com.android.cooper.app.navigation.log;

import android.content.Context;
import android.os.Environment;

import com.android.common.utils.ApplicationUtils;
import com.android.common.utils.ExecutorsKt;
import com.android.common.utils.FileLogger;
import com.android.common.utils.LogUtil;
import com.android.common.utils.ProcessUtil;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.io.File;
import java.util.Arrays;

import static com.android.common.utils.LogUtil.Dir.LOG_DIR;

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
public class LogTreeConfig {
    private static final String LOG_BASE_DIR = "MIUI/debug_log/navigation";
    private static final String LOG_FILE_PREFIX = "navigation";
    private static final int MAX_LOG_FILE_COUNT = 10;

    static {
        LOG_DIR = initLogDir();
    }

    public static void main() {
        initLogger();
    }

    private static Context getContext() {
        return ApplicationUtils.getApplication();
    }

    private static String initLogDir() {
        File logDir;
        if (XXPermissions.isGrantedPermission(getContext(), Permission.Group.STORAGE)) {
            logDir = new File(Environment.getExternalStorageDirectory(), LOG_BASE_DIR);
        } else {
            logDir = new File(ApplicationUtils.getApplication().getFilesDir(), "logs");
        }
        return logDir.getAbsolutePath();
    }

    private static void initLogger() {
        String processName = ProcessUtil.getCurrentProcessName(getContext());
        String processNameSuffix = ProcessUtil.getCurrentProcessNameSuffix(processName);
        LogUtil.setDebug(isDebug());
        FileLogger fileLogger = new FileLogger(LOG_DIR, LOG_FILE_PREFIX, processNameSuffix);
        fileLogger.setFileCreateListener(LogTreeConfig::clean);
        LogUtil.setFileLogger(fileLogger);
    }

    private static boolean isDebug() {
        return true;
    }

    private static void clean() {
        ExecutorsKt.serialExecute(() -> {
            if (LOG_DIR == null) {
                return;
            }
            File logDir = new File(LOG_DIR);
            File[] logFiles = logDir.listFiles((dir, name) -> name.startsWith(LOG_FILE_PREFIX));

            if (logFiles != null && logFiles.length > MAX_LOG_FILE_COUNT) {
                Arrays.sort(
                        logFiles,
                        (lhs, rhs) -> Long.compare(rhs.lastModified(), lhs.lastModified())
                );
                int size = logFiles.length;
                for (int i = MAX_LOG_FILE_COUNT; i < size; i++) {
                    //noinspection ResultOfMethodCallIgnored
                    logFiles[i].delete();
                }
            }
        });
    }
}
