package microsservices.micro_gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("Aconteceu um erro! Por favor, tente novamente em alguns minutos ou entre em contato com o suporte.");
    }
}

