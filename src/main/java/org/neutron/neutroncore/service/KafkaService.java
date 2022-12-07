package org.neutron.neutroncore.service;

public interface KafkaService {
	/**
	 *
	 * @param topic
	 * @param data
	 */
	void sendInfo(String topic, String data);

	/**
	 *
	 * @param topic
	 * @param data
	 */
	void sendProducerInfo(String topic, String data);
}
