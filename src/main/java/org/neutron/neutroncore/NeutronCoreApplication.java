package org.neutron.neutroncore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NeutronCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeutronCoreApplication.class, args);
	}

}
