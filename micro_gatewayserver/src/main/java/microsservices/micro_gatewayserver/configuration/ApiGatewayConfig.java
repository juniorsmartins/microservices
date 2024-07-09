package microsservices.micro_gatewayserver.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator microservicesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(rota -> rota
                .path("/microcustomers/**")
                .filters(filtro -> filtro.rewritePath("/microcustomers/(?<segment>.*)","/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .circuitBreaker(config -> config.setName("microcustomersCircuitBreaker") // Pode usar qualquer nome para o Circuit Breaker
                        .setFallbackUri("forward:/contactSupport"))) // Será acionado o fallback sempre que ocorrer erro
                .uri("lb://MICROCUSTOMERS")
            )
            .route(rota ->
                rota.path("/microempresas/**")
                .filters(filtro -> filtro.rewritePath("/microempresas/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .circuitBreaker(config -> config.setName("microempresasCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                .uri("lb://MICROEMPRESAS")
            )
            .route(rota ->
                rota.path("/microemails/**")
                .filters(filtro -> filtro.rewritePath("/microemails/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .circuitBreaker(config -> config.setName("microemailsCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                .uri("lb://MICROEMAILS")
            ).build();
    }
}

