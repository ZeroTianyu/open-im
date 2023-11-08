package com.openim.msg.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.openim.msg.service.SessionFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettySocketEventHandler {

    @Value("${spring.cloud.stream.bindings.message-in-0.destination}")
    private String bindingName;

    @Resource
    private StreamBridge streamBridge;

    //socket事件消息接收入口
    @OnEvent(value = "chatevent") //value值与前端自行商定
    public void onEvent(SocketIOClient client, AckRequest ackRequest, String msg) {
        streamBridge.send(bindingName, msg);
    }

    //socket添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        SessionFactory.getSession().unbind(client);
        client.disconnect();
        log.info("--------------------客户端已断开连接--------------------");

    }

    //socket添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            String username = client.getHandshakeData().getSingleUrlParam("username");

            SessionFactory.getSession().bind(username, client);

            log.info(username + "--------------------客户端连接成功---------------------");
        } else {
            log.error("客户端为空");
        }
    }
}