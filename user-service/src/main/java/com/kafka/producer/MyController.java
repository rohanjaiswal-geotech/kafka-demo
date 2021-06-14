package com.kafka.producer;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private String response;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Autowired
    UserRepository userRepository;

    @KafkaListener(id = "myId", topics = "topic2")
    public void listen(String json) {
        response = json;
    }

    @RequestMapping("/test")
    public String test(@RequestParam("username") String username ) {
        try {
            String userName = userRepository.findByUserName(username).getUserName();
            template.send("topic1", userName);
            while (response == null) ;
            String temp = response;
            response = null;
            return temp;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

}
