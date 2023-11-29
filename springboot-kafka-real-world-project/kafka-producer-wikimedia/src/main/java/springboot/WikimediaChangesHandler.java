package in.kafka.springboot;

import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

import ch.qos.logback.classic.Logger;

public class WikimediaChangesHandler implements EventHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private String topic;
	
	
	public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate,String topic) {
		this.kafkaTemplate=kafkaTemplate;
		this.topic= topic;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		
		LOGGER.info("event data " + messageEvent.getData());
		
		kafkaTemplate.send(topic,messageEvent.getData());
		
		
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	

}
