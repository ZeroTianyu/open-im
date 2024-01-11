package com.openim.msg.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.handler.SocketIOException;
import com.openim.common.model.jwt.JwtPayload;
import com.openim.common.utils.RedisUtil;
import com.openim.msg.constant.SocketIOEventConstant;
import com.openim.msg.constant.UrlParamConstant;
import com.openim.msg.service.SessionFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketEventHandler {

    @Value("${spring.cloud.stream.bindings.message-in-0.destination}")
    private String bindingName;
    @Resource
    private StreamBridge streamBridge;
    @Resource
    private RedisUtil redisUtil;

    //socket事件消息接收入口
    @OnEvent(value = SocketIOEventConstant.CHAT) //value值与前端自行商定
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Object msg) {
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
            // 从url中获取token信息
            String token = client.getHandshakeData().getSingleUrlParam(UrlParamConstant.TOKEN);
            // 从jwt中解析出用户信息
            JSONObject payloads = JWTUtil.parseToken(token).getPayloads();
            JwtPayload payload = payloads.toBean(JwtPayload.class);
            // 绑定服务器
            SessionFactory.getSession().bind(payload.getUserId(), client);
            redisUtil.set("name", payload.getSub());
            log.info(payload.getSub() + "--------------------客户端连接成功---------------------");
        } else {
            throw new SocketIOException("客户端为空");
        }
    }
}