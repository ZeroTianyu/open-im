package com.openim.msg.handler;

import com.alibaba.fastjson2.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class NettySocketEventHandler {
    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap =
            new ConcurrentHashMap<>();

    //socket事件消息接收入口
    @OnEvent(value = "message_event") //value值与前端自行商定
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Object data) {
        //根据msgType类别进行数据类型判断,
//        if (data.getMsgType().equals("XXXXXData")) { //数据类型标识
//            client.sendEvent("message_event", "已成功接收数据"); //向前端发送接收数据成功标识
//
//            //data.getMsgContent()获取前端推送数据
//            //......这里可填写接收数据后的相关业务逻辑代码
//        }
        log.info("接收到消息：{}", JSON.toJSONString(data));
    }

    //socket添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("--------------------客户端已断开连接--------------------");
        client.disconnect();
    }

    //socket添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            HandshakeData client_mac = client.getHandshakeData();
            String mac = client_mac.getUrl();
            //存储SocketIOClient，用于向不同客户端发送消息
            socketIOClientMap.put(mac, client);

            log.info("--------------------客户端连接成功---------------------");
        } else {
            log.error("客户端为空");
        }
    }

    /**
     * 广播消息 函数可在其他类中调用
     */
    public static void sendBroadcast(byte[] data) {
        for (SocketIOClient client : socketIOClientMap.values()) { //向已连接的所有客户端发送数据,map实现客户端的存储
            if (client.isChannelOpen()) {
                client.sendEvent("message_event", data);
            }
        }
    }
}