package cn.pbj.demo2020.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @pClassName: WebConfig
 * @author: pengbingjiang
 * @create: 2020/6/26 19:31
 * @description: TODO  WebSocket config配置类
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
