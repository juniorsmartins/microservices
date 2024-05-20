package microservices.micro_customers;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import microservices.micro_customers.adapter.dto.response.ContactInfoDtoResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Documentação da Rest API Customers.",
		description = "A API Customers faz parte do microsserviços, Mercado Financeiro.",
		version = "v1",
		contact = @Contact(
			name = "Junior Martins",
			email = "teste@teste.com",
			url = "https://www.teste.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.teste.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Documentação externa da API Customers",
		url = "https://www.teste.com/docs"
	)
)
@RefreshScope
@EnableConfigurationProperties(value = ContactInfoDtoResponse.class)
public class MicroCustomersApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroCustomersApplication.class, args);
	}
}

