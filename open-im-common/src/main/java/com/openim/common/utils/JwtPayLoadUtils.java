package com.openim.common.utils;


import com.alibaba.fastjson2.JSON;
import com.openim.common.model.jwt.JwtPayload;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

/**
 * 获取当前用户信息
 */
public class JwtPayLoadUtils {


    public static JwtPayload getJwtPayLoad() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> tokenAttributes = authentication.getTokenAttributes();
        return JSON.parseObject(JSON.toJSONString(tokenAttributes), JwtPayload.class);
    }
}
