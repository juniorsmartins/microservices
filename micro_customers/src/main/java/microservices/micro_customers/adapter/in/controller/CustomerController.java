package microservices.micro_customers.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.mapper.MapperIn;
import microservices.micro_customers.application.port.input.CustomerCreateInputPort;
import microservices.micro_customers.application.port.output.CustomerSearchOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerCreateInputPort customerCreateInputPort;

    private final CustomerSearchOutputPort customerSearchOutputPort;

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

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<CustomerSearchDtoResponse>> search(final CustomerFilter customerFilter,
        @PageableDefault(sort = "customerId", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao) {

        var response = Optional.ofNullable(customerFilter)
            .map(filter -> this.customerSearchOutputPort.search(filter, paginacao))
            .orElseThrow();

        return ResponseEntity
            .ok()
            .body(response);
    }

}

