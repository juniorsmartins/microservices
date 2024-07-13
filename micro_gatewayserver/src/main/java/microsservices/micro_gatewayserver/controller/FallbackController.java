package microsservices.micro_gatewayserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {

        log.error("Tentativa (retry) acionada em /contactSupport");

        return Mono.just("Aconteceu um erro! Por favor, tente novamente ou entre em contato com o suporte.");
    }
}

