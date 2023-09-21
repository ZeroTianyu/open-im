package com.openim.conversation;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@ServerEndpoint("/trtc/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {



    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.warn("=========== 收到来自窗口" + session.getId() + "的信息:" + message);

        handleTextMessage(session, new TextMessage(message));
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable error) {
        log.error("=========== 发生错误");
        error.printStackTrace();
//        msgStore.deleteSession(session);
    }

    public void handleTextMessage(Session session, TextMessage message) throws Exception {
        log.warn("=========== Received message: {}", message.getPayload());
    }
}
