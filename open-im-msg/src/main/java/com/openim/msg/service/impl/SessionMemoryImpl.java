package com.openim.msg.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.openim.msg.service.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMemoryImpl implements Session {

    private static Map<Long, SocketIOClient> userIdClientMap = new ConcurrentHashMap<>();
    private static Map<SocketIOClient, Long> clientUserIdMap = new ConcurrentHashMap<>();

    @Override
    public void bind(Long userId, SocketIOClient socketIOClient) {
        userIdClientMap.put(userId, socketIOClient);
        clientUserIdMap.put(socketIOClient, userId);
    }

    @Override
    public void unbind(SocketIOClient socketIOClient) {
        Long userId = clientUserIdMap.get(socketIOClient);
        userIdClientMap.remove(userId);
        clientUserIdMap.remove(socketIOClient);
    }

    @Override
    public Object getAttribute(Long userId, SocketIOClient socketIOClient) {
        return null;
    }

    @Override
    public void setAttribute(SocketIOClient socketIOClient, String name, Object value) {

    }

    @Override
    public SocketIOClient getSocketIOClient(Long userId) {
        return userIdClientMap.get(userId);
    }

    @Override
    public String toString() {
        return userIdClientMap.toString();
    }
}
