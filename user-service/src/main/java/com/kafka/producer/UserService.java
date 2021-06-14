package com.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class UserService {

	public static void main(String[] args) {
		SpringApplication.run(UserService.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic topic2() {
		return TopicBuilder.name("topic2")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@KafkaListener(id = "myId", topics = "topic2")
	public void listen(String in) {
		System.out.println(in);
	}

}
