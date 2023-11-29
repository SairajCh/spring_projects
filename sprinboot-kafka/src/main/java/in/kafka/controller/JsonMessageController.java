package in.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kafka.kafkamain.JsonKafkaProducer;
import in.kafka.payload.User;

@RestController


public class JsonMessageController {
	
	@Autowired
	private JsonKafkaProducer jsonKafkaProducer;
	
	@PostMapping("publish")
	public ResponseEntity<String> publish(@RequestBody User user){
		 jsonKafkaProducer.sendMessage(user);
		 return ResponseEntity.ok("Json message sent to kafka Topic");
		
	}
	
}
