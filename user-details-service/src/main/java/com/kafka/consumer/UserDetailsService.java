package com.kafka.consumer;

import com.kafka.consumer.models.UserDetails;
import com.kafka.consumer.models.UserDetailsRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class UserDetailsService {

	@Autowired
	private KafkaTemplate<String, String> template;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserDetailsService.class, args);
	}

	@Bean
	public NewTopic topic1() {
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

	@KafkaListener(id = "myId", topics = "topic1")
	public void listen(String in) {
		System.out.println(in);
		UserDetails userDetails = userDetailsRepository.findByUserName(in);
		template.send("topic2", userDetails.toString());
	}

}
