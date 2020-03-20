package com.shengsiyuan.netty.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by andy
 * 2020/3/20.
 * Email: 1239604859@qq.com
 */

public class TaskManager {
    private static final TaskManager ourInstance = new TaskManager();

    static TaskManager getInstance() {
        return ourInstance;
    }

    private TaskManager() {
    }

    private ExecutorService mService = Executors.newCachedThreadPool();

    public void execute(Runnable command) {
        mService.execute(command);
    }
}
