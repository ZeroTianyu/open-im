package com.openim.msg.service;

import com.openim.msg.service.impl.SessionMemoryImpl;

/**
 * 会话工具类
 */
public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    /**
     * 获取会话
     *
     * @return
     */
    public static Session getSession() {
        return session;
    }
}
