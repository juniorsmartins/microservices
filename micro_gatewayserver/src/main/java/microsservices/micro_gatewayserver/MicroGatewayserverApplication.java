package microsservices.micro_gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroGatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator microservicesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
			.route(rota -> rota.path("/microservices/micro_customers/**")
				.filters(filtro -> filtro.rewritePath("/microservices/micro_customers/(?<segment>.*)","/${segment}"))
				.uri("lb://MICRO_CUSTOMERS")
			)
			.route(rota -> rota.path("/microservices/micro_empresas/**")
				.filters(filtro -> filtro.rewritePath("/microservices/micro_empresas/(?<segment>.*)", "/${segment}"))
				.uri("lb://MICRO_EMPRESAS")
			)
			.route(rota -> rota.path("/microservices/micro_emails/**")
					.filters(filtro -> filtro.rewritePath("/microservices/micro_emails/(?<segment>.*)", "/${segment}"))
					.uri("lb://MICRO_EMAILS")
			).build();
	}
}

