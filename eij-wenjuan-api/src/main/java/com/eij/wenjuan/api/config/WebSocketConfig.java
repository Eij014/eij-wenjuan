package com.eij.wenjuan.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-05
 */
@Configuration
public class WebSocketConfig {
    /**
     * ServerEndpointExporter
     * 自动注册使用@ServerEndpointExporter注解声明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
