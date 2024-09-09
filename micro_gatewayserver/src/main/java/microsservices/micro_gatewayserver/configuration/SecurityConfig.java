package microsservices.micro_gatewayserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        // serverHttpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll()) // Nenhuma requisição precisa ser autenticada
        // serverHttpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated()) // Todas as requisições precisam ser autenticadas
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll() // Especifica o tipo de requisição que precisa e que não precisa de autenticação
                .pathMatchers("/microcustomers/**").authenticated()
                .pathMatchers("/microempresas/**").authenticated()
                .pathMatchers("/microemails/**").authenticated())
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(Customizer.withDefaults()));

        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }
}

