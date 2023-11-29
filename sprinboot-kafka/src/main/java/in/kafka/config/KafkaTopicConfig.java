package in.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@Configurable
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic sairajTopic() {
		return TopicBuilder.name("sairaj")
				.build();
	}

	
	@Bean
	public NewTopic sairajJsonTopic() {
		return TopicBuilder.name("sairaj_json")
				.build();
	}
}
