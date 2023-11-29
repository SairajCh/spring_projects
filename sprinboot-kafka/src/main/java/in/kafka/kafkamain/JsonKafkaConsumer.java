package in.kafka.kafkamain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import in.kafka.payload.User;

@Service
public class JsonKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
	
	@KafkaListener(topics="sairaj_json",groupId="myGroup")
	public void consumer(User user) {
		
		LOGGER.info("Json message received"+user.toString());
		
		
	}
}
