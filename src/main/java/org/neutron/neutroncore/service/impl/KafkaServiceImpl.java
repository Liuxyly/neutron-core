package org.neutron.neutroncore.service.impl;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.neutron.neutroncore.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class KafkaServiceImpl implements KafkaService {
	private Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

	@Resource
	private KafkaTemplate<Object, Object> template;

	@Override
	public void sendInfo(String topic, String data) {
		template.send(topic, data);
		logger.info("{}:{}", topic, data);
	}

	@Override
	public void sendProducerInfo(String topic, String data) {
		ProducerRecord<Object, Object> pr = new ProducerRecord<>(topic, data);
		template.send(pr);
		logger.info("{}:{}", topic, data);
	}

	@KafkaListener(id = "webGroup", topics = "topic_input")
	public void listen(String input) {
		logger.info("input value: {}" , input);
	}

	@KafkaListener(id = "webGroup1", topics = "hello")
	public void onMessage(ConsumerRecord<Object, Object> record, Acknowledgment ack,
						  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		logger.info("单条消费消息：{}----{}----{}" , record.topic(), record.partition(), record.value());
		ack.acknowledge();
	}

	@KafkaListener(id = "webGroup2", topics = "hello")
	public void onMessageButch(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
		for(ConsumerRecord<?, ?> record:records) {
			logger.info("批量消费消息：{}----{}----{}" , record.topic(), record.partition(), record.value());
		}
		ack.acknowledge();
	}
}
