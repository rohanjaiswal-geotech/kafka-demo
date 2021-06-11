package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/test")
    public String test(@RequestParam("message") String message ) {
        template.send("topic1", message);
        return "Message Published";
    }
}
