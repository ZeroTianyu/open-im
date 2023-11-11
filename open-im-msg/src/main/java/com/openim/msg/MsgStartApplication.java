package com.openim.msg;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.net.InetAddress;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class MsgStartApplication {
    @Resource
    private SocketIOServer server;

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.serviceName}")
    private String serviceName;
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    public static void main(String[] args) {
        SpringApplication.run(MsgStartApplication.class, args);
    }

    @PostConstruct
    private void init() {
        server.start();
        registerNamingService(serviceName, port);
    }

    /**
     * 注册到 nacos 服务中
     *
     * @param nettyName netty服务名称
     * @param nettyPort netty服务端口
     */
    private void registerNamingService(String nettyName, Integer nettyPort) {
        try {
            NamingService namingService = NamingFactory.createNamingService(nacosDiscoveryProperties.getServerAddr());
            InetAddress address = InetAddress.getLocalHost();
            namingService.registerInstance(nettyName, address.getHostAddress(), nettyPort);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    private void destroy() {
        server.stop();
    }
}
