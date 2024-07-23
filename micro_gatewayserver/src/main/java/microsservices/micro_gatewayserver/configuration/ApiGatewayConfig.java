package microsservices.micro_gatewayserver.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver())) // RateLimiter com Redis - limita número de requisições para manter disponibildiade e impedir ataques Ddos.
                    .retry(retryConfig -> retryConfig.setRetries(2) // Define o número máximo de tentativas automáticas de requisições em caso de falhas
                        .setMethods(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH) // Diz que o retry será aplicado somente aos métodos especificados
                        .setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true)) // Define a estratégia de backoff exponencial. O Gateway aumentará o tempo de espera entre tentativas (de 100ms para 1000ms) até a tentativa final, dobrando o tempo a cada tentativa. O parâmetro true indica que o tempo máximo de backoff será multiplicado pelo fator de multiplicação em cada tentativa.
                    .circuitBreaker(config -> config.setName("microcustomersCircuitBreaker") // Pode usar qualquer nome para o Circuit Breaker
                        .setFallbackUri("forward:/contactSupport"))) // Será acionado o fallback sempre que ocorrer erro
                        .metadata(CONNECT_TIMEOUT_ATTR, 5000) // Tempo máximo que o Gateway espera para edockerstabelecer uma conexão com o serviço de destino
                        .metadata(RESPONSE_TIMEOUT_ATTR, 25000) // Tempo máximo que o Gateway espera para receber uma resposta do serviço de destino após a conexão ser estabelecida
                .uri("lb://MICROCUSTOMERS")
            )
            .route(rota ->
                rota.path("/microempresas/**")
                .filters(filtro -> filtro.rewritePath("/microempresas/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                    .retry(retryConfig -> retryConfig.setRetries(2)
                        .setMethods(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH)
                        .setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true))
                    .circuitBreaker(config -> config.setName("microempresasCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 3500)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 6000)
                .uri("lb://MICROEMPRESAS")
            )
            .route(rota ->
                rota.path("/microemails/**")
                .filters(filtro -> filtro.rewritePath("/microemails/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                    .retry(retryConfig -> retryConfig.setRetries(2)
                        .setMethods(HttpMethod.GET, HttpMethod.POST)
                        .setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true))
                    .circuitBreaker(config -> config.setName("microemailsCircuitBreaker")
                        .setFallbackUri("forward:/contactSupport")))
                        .metadata(CONNECT_TIMEOUT_ATTR, 3500)
                        .metadata(RESPONSE_TIMEOUT_ATTR, 5000)
                .uri("lb://MICROEMAILS")
            ).build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() { // Uso do Redis como RateLimiter, com valores para ReplenishRate, BurstCapacity e RequestedTokens
        return new RedisRateLimiter(5, 10, 10);
        // ReplenishRate - define a taxa de atualização (replenish rate) em unidades por segundo.
        // BurstCapacity - define a capacidade de burst. Indica quantas requisições podem ser feitas acima da taxa limite em curto período.
        // RequestedTokens - define o tempo de retenção dos tokens em segundos.
    }

    @Bean
    KeyResolver userKeyResolver() { // RateLimiter - estratégia de limitar requisições por usuário conectado
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }
}

