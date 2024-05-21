package microsservices.micro_gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MicroGatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGatewayserverApplication.class, args);
	}
}

