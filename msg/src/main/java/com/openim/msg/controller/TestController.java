package com.openim.msg.controller;

import com.openim.msg.consumer.LogMessageProducer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private LogMessageProducer logMessageProducer;

    @GetMapping("/test")
    public String test() {
        logMessageProducer.logProducer("/topic-name", "dassdgdfdhanyhbupobsuhiosbn");
        return "test";
    }
}
