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

	@Bean
	public RouteLocator microservicesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
			.route(rota -> rota.path("/microservices/microcustomers/**")
				.filters(filtro -> filtro.rewritePath("/microservices/microcustomers/(?<segment>.*)","/${segment}")
					.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				.uri("lb://MICROCUSTOMERS")
			)
			.route(rota -> rota.path("/microservices/microempresas/**")
				.filters(filtro -> filtro.rewritePath("/microservices/microempresas/(?<segment>.*)", "/${segment}")
					.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				.uri("lb://MICROEMPRESAS")
			)
			.route(rota -> rota.path("/microservices/microemails/**")
				.filters(filtro -> filtro.rewritePath("/microservices/microemails/(?<segment>.*)", "/${segment}")
					.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				.uri("lb://MICROEMAILS")
			).build();
	}
}

