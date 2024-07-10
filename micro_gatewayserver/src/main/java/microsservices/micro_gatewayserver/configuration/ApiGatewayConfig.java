package microsservices.micro_gatewayserver.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

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
                        .metadata(CONNECT_TIMEOUT_ATTR, 3000) // Tempo máximo que o Gateway espera para estabelecer uma conexão com o serviço de destino
                        .metadata(RESPONSE_TIMEOUT_ATTR, 8000) // Tempo máximo que o Gateway espera para receber uma resposta do serviço de destino após a conexão ser estabelecida
                .uri("lb://MICROCUSTOMERS")
            )
            .route(rota ->
                rota.path("/microempresas/**")
                .filters(filtro -> filtro.rewritePath("/microempresas/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .circuitBreaker(config -> config.setName("microempresasCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 3000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 5000)
                .uri("lb://MICROEMPRESAS")
            )
            .route(rota ->
                rota.path("/microemails/**")
                .filters(filtro -> filtro.rewritePath("/microemails/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .circuitBreaker(config -> config.setName("microemailsCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 3000)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 4000)
                .uri("lb://MICROEMAILS")
            ).build();
    }
}

