package com.shengsiyuan.kotlin8;

import java.io.IOException;

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */
class JavaException {

    public void openFile() throws IOException {
        throw new IOException("I/O 异常");
    }
}
