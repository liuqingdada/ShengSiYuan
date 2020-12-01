package com.shengsiyuan.jdk6;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

/**
 * Created by cooper
 * 2020/11/30.
 * Email: 1239604859@qq.com
 */
class JavaCompilerTest {
    public static void main(String[] args) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        File jFile = new File("src/main/resources/UncheckedTest.java");
        System.out.println(jFile.getAbsolutePath());

        int ret = javaCompiler.run(null, null, null, jFile.getPath());
        System.out.println(ret);
    }
}
