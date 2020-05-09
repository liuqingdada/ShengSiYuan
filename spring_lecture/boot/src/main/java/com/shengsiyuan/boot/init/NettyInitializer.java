package com.shengsiyuan.boot.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

/**
 * Created by andy
 * 2020/1/21.
 * Email: 1239604859@qq.com
 */

@HandlesTypes(WebApplicationInitializer.class)
public class NettyInitializer implements WebApplicationInitializer {
    private static Logger logger = LoggerFactory.getLogger(NettyInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.debug("netty initializer startup.");
    }
}
