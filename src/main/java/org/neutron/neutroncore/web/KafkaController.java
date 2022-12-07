package org.neutron.neutroncore.web;

import org.neutron.neutroncore.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/kafka")
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;

	@GetMapping("/send_info")
	public void testSendInfo(String topic, String data) {
		kafkaService.sendInfo(topic, data);
	}
}
