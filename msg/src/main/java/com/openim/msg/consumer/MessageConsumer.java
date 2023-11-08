package com.openim.msg.consumer;

import com.alibaba.fastjson2.JSON;
import com.corundumstudio.socketio.SocketIOClient;
import com.openim.msg.model.SendMegRequest;
import com.openim.msg.service.SessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Configuration
public class MessageConsumer {

    /**
     * 消费者，bean名称必须与配置文件中的bindingName一致
     *
     * @return
     */
    @Bean
    Consumer<List<String>> message() {
        return list -> list.forEach(request -> {
            SendMegRequest sendMegRequest = JSON.parseObject(request, SendMegRequest.class);
            SocketIOClient socketIOClient = SessionFactory.getSession().getSocketIOClient(sendMegRequest.getRecvID());
            if (socketIOClient != null) {
                socketIOClient.sendEvent("chatevent", sendMegRequest);
            }

            log.info("收到消息:{}", request);
        });
    }

}