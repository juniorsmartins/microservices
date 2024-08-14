package microsservices.micro_gatewayserver.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import net.datafaker.Faker;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
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

@Configuration
public class ApiGatewayConfig {

    private static final String RESPONSE_TIME = "X-Response-Time";

    public static final String PATH_SEGMENT = "/${segment}";

    @Bean
    public RouteLocator microservicesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(rota -> rota
                .path("/microcustomers/**")
                .filters(filtro -> filtro.rewritePath("/microcustomers/(?<segment>.*)", PATH_SEGMENT)
                        .addResponseHeader(RESPONSE_TIME, LocalDateTime.now().toString())
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver())) // RateLimiter com Redis - limita número de requisições para manter disponibildiade e impedir ataques Ddos.
                    .retry(retryConfig -> retryConfig.setRetries(2) // Define o número máximo de tentativas automáticas de requisições em caso de falhas
                        .setMethods(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH) // Diz que o retry será aplicado somente aos métodos especificados
                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)) // Define a estratégia de backoff exponencial. O Gateway aumentará o tempo de espera entre tentativas até a tentativa final, dobrando o tempo a cada tentativa. O parâmetro true indica que o tempo máximo de backoff será multiplicado pelo fator de multiplicação em cada tentativa.
                    .circuitBreaker(config -> config.setName("microcustomersCircuitBreaker") // Pode usar qualquer nome para o Circuit Breaker
                        .setFallbackUri("forward:/customersContactSupport"))) // Será acionado o fallback sempre que ocorrer erro
    //                  .metadata(CONNECT_TIMEOUT_ATTR, 3_000) // Tempo máximo que o Gateway espera para estabelecer uma conexão com o serviço de destino
    //                  .metadata(RESPONSE_TIMEOUT_ATTR, 3_000) // Tempo máximo que o Gateway espera para receber uma resposta do serviço de destino após a conexão ser estabelecida
                .uri("lb://MICROCUSTOMERS")
            )
            .route(rota -> rota
                .path("/microempresas/**")
                .filters(filtro -> filtro.rewritePath("/microempresas/(?<segment>.*)", PATH_SEGMENT)
                    .addResponseHeader(RESPONSE_TIME, LocalDateTime.now().toString())
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                    .retry(retryConfig -> retryConfig.setRetries(2)
                        .setMethods(HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH)
                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
                    .circuitBreaker(config -> config.setName("microempresasCircuitBreaker")
                        .setFallbackUri("forward:/empresasContactSupport")))
                .uri("lb://MICROEMPRESAS")
            )
            .route(rota -> rota
                .path("/microemails/**")
                .filters(filtro -> filtro.rewritePath("/microemails/(?<segment>.*)", PATH_SEGMENT)
                    .addResponseHeader(RESPONSE_TIME, LocalDateTime.now().toString())
                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                    .retry(retryConfig -> retryConfig.setRetries(3)
                        .setMethods(HttpMethod.GET)
                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
                    .circuitBreaker(config -> config.setName("microemailsCircuitBreaker")
                        .setFallbackUri("forward:/emailsContactSupport")))
                .uri("lb://MICROEMAILS")
            ).build();
    }

    @Bean // Fallback padrão do CB
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(30))
                        .build()).build());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() { // Uso de Redis como RateLimiter, com valores para ReplenishRate, BurstCapacity e RequestedTokens
        // ReplenishRate - define a taxa de atualização (replenish rate) em unidades por segundo.
        // BurstCapacity - define a capacidade de burst. Indica quantas requisições podem ser feitas acima da taxa limite em curto período.
        // RequestedTokens - define o tempo de retenção do tokem em segundos. (obs: deixar esse valor bem baixo, pois deu muito problema de http status 429)
        return new RedisRateLimiter(5, 10, 1);
    }

    @Bean
    KeyResolver userKeyResolver() { // Define como as chaves são geradas para o RateLimiter. Neste caso, usamos a estratégia de limitar requisições por usuário conectado. Há outras estratégias

        Faker faker = new Faker();
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty(faker.lorem().characters(10)); // Fiz gambiarra para substituir o "anonymous" por faker randômico
    }
}

