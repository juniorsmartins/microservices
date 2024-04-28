package microservices.micro_emails.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "emails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ContactInfoDtoResponse {

    private String message;

    private Map<String, String> contactDetails;

    private Set<String> onCallSupport;

}

