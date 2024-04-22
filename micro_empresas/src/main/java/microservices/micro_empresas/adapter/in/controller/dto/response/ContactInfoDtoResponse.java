package microservices.micro_empresas.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "contacts")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactInfoDtoResponse(

        String message,

        Map<String, String> contactDetails,

        Set<String> onCallSupport

) { }

