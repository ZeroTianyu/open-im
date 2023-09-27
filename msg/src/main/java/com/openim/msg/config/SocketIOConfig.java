package com.openim.msg.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.openim.msg.model.ChatObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocketIOConfig {

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.bossCount}")
    private int bossCount;

    @Value("${socketio.workCount}")
    private int workCount;

    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private int pingTimeout;

    @Value("${socketio.pingInterval}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        // 不可指定hostname，指定之后无法通过网关负载均衡
//        config.setHostname("localhost");
        config.setPort(port);
        config.setTransports(Transport.WEBSOCKET);
        config.setAuthorizationListener(data -> {
            String token = data.getSingleUrlParam("token");
//            log.info(token);
            return true;
        });

        final SocketIOServer server = new SocketIOServer(config);

        server.addEventListener("chatevent", ChatObject.class, (client, data, ackRequest) -> {
            // broadcast messages to all clients
            server.getBroadcastOperations().sendEvent("chatevent", data);
        });
        return server;
    }

    /**
     * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }
}