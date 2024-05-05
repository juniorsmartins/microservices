package microservices.micro_emails;

import microservices.micro_emails.adapter.dto.response.ContactInfoDtoResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ContactInfoDtoResponse.class)
public class MicroEmailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroEmailsApplication.class, args);
	}

}
