package com.openim.msg.consumer;

import jakarta.annotation.Resource;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;


@Component
public class LogMessageProducer {

    // 这个地方必须要注入这个，因为都是基于这个来的。
    @Resource
    private StreamBridge streamBridge;


    public void logProducer(String topicName, String logJsonInfo) {
        // 这个地方也可以使用实体类啥的，都一样。
        streamBridge.send(topicName, logJsonInfo);
    }
}