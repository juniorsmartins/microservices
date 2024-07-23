package microsservices.micro_gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class MicroGatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGatewayserverApplication.class, args);
	}
}

