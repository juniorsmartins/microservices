package microservices.micro_customers.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "customers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactInfoDtoResponse(

    String message,

    Map<String, String> contactDetails,

    Set<String> onCallSupport

) { }

