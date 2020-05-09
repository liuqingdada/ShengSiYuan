package com.shengsiyuan.boot.controller;

import com.shengsiyuan.boot.websocket.DefaultEchoService;
import com.shengsiyuan.boot.websocket.EchoService;
import com.shengsiyuan.boot.websocket.EchoWebSocketHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by andy
 * 2020/1/15.
 * Email: 1239604859@qq.com
 */

@EnableWebSocket
@Configuration
public class EchoWebSocketController implements WebSocketConfigurer {
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
