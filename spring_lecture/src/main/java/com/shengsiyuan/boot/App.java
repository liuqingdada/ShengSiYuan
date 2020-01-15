package com.shengsiyuan.boot;

import com.shengsiyuan.boot.websocket.DefaultEchoService;
import com.shengsiyuan.boot.websocket.EchoService;
import com.shengsiyuan.boot.websocket.EchoWebSocketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.PostConstruct;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 * <p>
 * 调试规范: 日志 远程调试
 * JDWP: Java Debug Wire Protocol, Java调试协议
 * java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000 -jar ***.jar
 */

@SpringBootApplication
@EnableWebSocket
public class App implements WebSocketConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug(App.class.getClassLoader().toString());
        // Default run
        SpringApplication.run(App.class, args);

        // Custom run
        /*SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);*/
    }

    @PostConstruct
    private void testLog() {
        logger.trace("Trace Message");
        logger.debug("Debug Message");
        logger.info("Info Message");
        logger.warn("Warn Message");
        logger.error("Error Message");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/ws");
                //.withSockJS();
    }

    @Bean
    EchoService echoService() {
        return new DefaultEchoService("You said: \"%s\"");
    }

    @Bean
    WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }
}
