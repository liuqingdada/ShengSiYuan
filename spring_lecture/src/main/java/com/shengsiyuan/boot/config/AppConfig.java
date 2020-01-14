package com.shengsiyuan.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 */

@Configuration
public class AppConfig {
    @Bean
    public AppConfigBean appConfigBean() {
        return new AppConfigBean();
    }
}
