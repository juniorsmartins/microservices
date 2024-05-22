package microsservices.micro_gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1) // Define a ordem dos filtros, em caso de haver vários
@Component
public class RequestTraceFilter implements GlobalFilter { // A interface GlobalFilter permite que o filtro seja executado para todos os tipos de tráfego recebidos pelo Gateway.

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("rastreamento-id found in RequestTraceFilter : {}",
                    filterUtility.getRastreamentoId(requestHeaders));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtility.setRastreamentoId(exchange, correlationID);
            logger.debug("rastreamento-id generated in RequestTraceFilter : {}", correlationID);
        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getRastreamentoId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateCorrelationId() { // Responsável por gerar um ID de Rastreamento ou Correlação
        return java.util.UUID.randomUUID().toString();
    }
}

