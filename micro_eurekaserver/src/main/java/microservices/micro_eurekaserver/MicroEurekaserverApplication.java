package microservices.micro_eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//@RefreshScope
@EnableEurekaServer
public class MicroEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroEurekaserverApplication.class, args);
	}
}

