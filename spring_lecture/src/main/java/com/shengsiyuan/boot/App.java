package com.shengsiyuan.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 * 调试规范: 日志 远程调试
 * JDWP: Java Debug Wire Protocol, Java调试协议
 * java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000 -jar ***.jar
 */

@SpringBootApplication
public class App {
    private static final Log logger = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        logger.debug(App.class.getClassLoader());
        SpringApplication.run(App.class, args);
    }

}
