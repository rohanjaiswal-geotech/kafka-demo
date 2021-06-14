package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private String message;

//    @KafkaListener(id = "myId", topics = "topic1")
//    public void listen(String in) {
//        System.out.println(in);
//        this.message = in;
//    }

    @RequestMapping("/fetch")
    public String fetch() {
        return this.message;
    }

}
