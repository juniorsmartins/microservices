package microservices.micro_customers.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.mapper.MapperIn;
import microservices.micro_customers.application.port.input.CustomerCreateInputPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerCreateInputPort customerCreateInputPort;

    private final MapperIn mapperIn;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerCreateDtoResponse> create(@RequestBody @Valid CustomerCreateDtoRequest customerCreateDtoRequest) {

        var response = Optional.ofNullable(customerCreateDtoRequest)
            .map(this.mapperIn::toCustomer)
            .map(this.customerCreateInputPort::create)
            .map(this.mapperIn::toCustomerCreateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/customers" + response.customerId()))
            .body(response);
    }

}

