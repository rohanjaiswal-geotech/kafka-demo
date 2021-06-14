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

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/test")
    public String test(@RequestParam("username") String username ) {

        String userName = userRepository.findByUserName(username).getUserName();
        template.send("topic1", userName);
        return "Message Published";
    }

}
