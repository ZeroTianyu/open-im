package com.openim.msg.consumer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class ApiLogMessageConsumer {

    @Bean
    public Consumer<String> apiServiceLog() {
        return message -> {
            // 校验一下有没有
            if (StringUtils.isBlank(message)) {
                return;
            }
            try {
                // 存在的话，转一下类型
                ApiServiceLog apiServiceLog = JSON.parseObject(message, ApiServiceLog.class);
                // 执行保存
                log.info("收到消息:{}",message);
            } catch (Exception e) {
                log.error("消费操作日志失败：{}", e.getMessage());
            }
        };
    }
}