package microsservices.micro_gatewayserver.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator microservicesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(rota -> rota.path("/microcustomers/**")
                .filters(filtro -> filtro.rewritePath("/microcustomers/(?<segment>.*)","/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                .uri("lb://MICROCUSTOMERS")
            )
            .route(rota -> rota.path("/microempresas/**")
                .filters(filtro -> filtro.rewritePath("/microempresas/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                .uri("lb://MICROEMPRESAS")
            )
            .route(rota -> rota.path("/microemails/**")
                .filters(filtro -> filtro.rewritePath("/microemails/(?<segment>.*)", "/${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                .uri("lb://MICROEMAILS")
            ).build();
    }

//    @Bean
//    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
//        Function<PredicateSpec, Buildable<Route>> function = p -> p.path("/get").uri("http://httpbin.org:80");
//
//        return routeLocatorBuilder.routes()
//            .route(function)
//            .build();
//    }

//    @Bean
//    public RouteLocator gatewayRouter2(RouteLocatorBuilder routeLocatorBuilder) {
//
//        return routeLocatorBuilder.routes()
//            .route(p -> p.path("/get")
//                .filters(f -> f
//                    .addRequestHeader("Chave Hello", "Valor World")
//                    .addRequestParameter("Chave Hello", "Valor World"))
//                .uri("http://httpbin.org:80"))
//            .route(p -> p.path("/microcustomers/**")
//                .uri("lb://microcustomers"))
//            .route(p -> p.path("/microempresas/**")
//                .uri("lb://microempresas"))
//            .route(p -> p.path("/microemails/**")
//                .uri("lb://microemails"))
//            .build();
//    }
}

