package microservices.micro_empresas;

import microservices.micro_empresas.adapter.in.controller.dto.response.ContactInfoDtoResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ContactInfoDtoResponse.class)
public class MicroEmpresasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroEmpresasApplication.class, args);
	}

}
