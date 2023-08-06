package com.industry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author lc
 */
@Configuration
public class WebSocketConfig {

    @Lazy(value = false)
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}