package com.openim.msg.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class ApiLogMessageConsumer {

    @Autowired
    StreamBridge bridge;
    @KafkaListener(id = "batch-out", topics = "batch-out")
    public void listen(String in) {
        System.out.println(in);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> IntStream.range(0, 100).forEach(i -> bridge.send("batch-in", ("\"test" + i + "\"").getBytes()));
    }

}



@Component
class NoTransactions {
    @Autowired
    StreamBridge bridge;

    @Bean
    Consumer<List<String>> master() {
        return list -> list.forEach(str -> {
            bridge.send("output-out-0", str.toUpperCase());
        });
    }

}