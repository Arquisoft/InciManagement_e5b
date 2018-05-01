package uo.asw.apacheKafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendIncidenceImpl implements SendIncidence {

	@Autowired
	private KafkaProducer kafkaProducer;
	
	private static final String topic = "incidences";
	
	@Override
	public void sendIncidence(String jsonStringIncidence) {
		kafkaProducer.send(topic, jsonStringIncidence);
	}

}
