package com.openim.msg.service;


import com.corundumstudio.socketio.SocketIOClient;

/**
 * 会话管理接口
 */
public interface Session {

    /**
     * 绑定会话
     *
     * @param userId       会话绑定用户
     * @param socketIOClient 哪个 socketIOClient 要绑定会话
     */
    void bind(Long userId, SocketIOClient socketIOClient);

    /**
     * 解绑会话
     *
     * @param socketIOClient 哪个 socketIOClient 要解绑会话
     */
    void unbind(SocketIOClient socketIOClient);

    /**
     * 获取属性
     *
     * @param name           属性名
     * @param socketIOClient 哪个 socketIOClient
     * @return 属性值
     */
    Object getAttribute(Long name, SocketIOClient socketIOClient);

    /**
     * 设置属性
     *
     * @param socketIOClient 哪个 socketIOClient
     * @param name           属性名
     * @param value          属性值
     */
    void setAttribute(SocketIOClient socketIOClient, String name, Object value);

    /**
     * 根据用户名获取 socketIOClient
     *
     * @param userId 用户id
     * @return SocketIOClient
     */
    SocketIOClient getSocketIOClient(Long userId);
}
