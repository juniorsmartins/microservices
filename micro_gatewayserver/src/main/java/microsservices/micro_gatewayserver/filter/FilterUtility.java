package microsservices.micro_gatewayserver.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class FilterUtility {

    public static final String RASTREAMENTO_ID = "rastreamento-id";

    public String getRastreamentoId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(RASTREAMENTO_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(RASTREAMENTO_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setRastreamentoId(ServerWebExchange exchange, String rastreamentoId) {
        return this.setRequestHeader(exchange, RASTREAMENTO_ID, rastreamentoId);
    }
}

