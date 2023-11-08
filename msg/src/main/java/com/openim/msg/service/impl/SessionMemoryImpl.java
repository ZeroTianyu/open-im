package com.openim.msg.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.openim.msg.service.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMemoryImpl implements Session {

    private static  Map<String, SocketIOClient> usernameClientMap = new ConcurrentHashMap<>();
    private static  Map<SocketIOClient, String> clientUsernameMap = new ConcurrentHashMap<>();

    @Override
    public void bind(String username, SocketIOClient socketIOClient) {
        usernameClientMap.put(username, socketIOClient);
        clientUsernameMap.put(socketIOClient, username);
    }

    @Override
    public void unbind(SocketIOClient socketIOClient) {
        String username = clientUsernameMap.get(socketIOClient);
        usernameClientMap.remove(username);
        clientUsernameMap.remove(socketIOClient);
    }

    @Override
    public Object getAttribute(String username, SocketIOClient socketIOClient) {
        return null;
    }

    @Override
    public void setAttribute(SocketIOClient socketIOClient, String name, Object value) {

    }

    @Override
    public SocketIOClient getSocketIOClient(String username) {
        return usernameClientMap.get(username);
    }

    @Override
    public String toString() {
        return usernameClientMap.toString();
    }
}
