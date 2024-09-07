package microsservices.micro_gatewayserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

    private static final String MSG_CONTATO_SUPORTE = "Algo deu errado! Por favor, tente novamente ou fale com o suporte. ";

    @RequestMapping("/customersContactSupport")
    public Mono<String> customersContactSupport() {
        log.error("Circuit Breaker aciona fallback em /customersContactSupport");
        return Mono.just(MSG_CONTATO_SUPORTE);
    }

    @RequestMapping("/empresasContactSupport")
    public Mono<String> empresasContactSupport() {
        log.error("Circuit Breaker aciona fallback em /empresasContactSupport");
        return Mono.just(MSG_CONTATO_SUPORTE);
    }

    @RequestMapping("/emailsContactSupport")
    public Mono<String> emailsContactSupport() {
        log.error("Circuit Breaker aciona fallback em /emailsContactSupport");
        return Mono.just(MSG_CONTATO_SUPORTE);
    }
}

