package microservices.micro_customers.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VersoesDtoResponse(

    String javaDistribution,

    String javaVersion

) { }
