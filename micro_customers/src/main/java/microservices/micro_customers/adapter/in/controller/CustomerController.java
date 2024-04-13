package microservices.micro_customers.adapter.in.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.request.CustomerUpdateDtoRequest;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.dto.response.CustomerUpdateDtoResponse;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.mapper.MapperIn;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.port.input.CustomerCreateInputPort;
import microservices.micro_customers.application.port.input.CustomerDeleteInputPort;
import microservices.micro_customers.application.port.input.CustomerUpdateInputPort;
import microservices.micro_customers.application.port.output.CustomerSearchOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(
    name = "CRUD da API Rest Customer.",
    description = "Customer disponibiliza os recursos: Create, Search, Update e Delete.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerCreateInputPort customerCreateInputPort;

    private final CustomerSearchOutputPort customerSearchOutputPort;

    private final CustomerDeleteInputPort customerDeleteInputPort;

    private final CustomerUpdateInputPort customerUpdateInputPort;

    private final MapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerCreateDtoResponse> create(@Valid @RequestBody CustomerCreateDtoRequest customerCreateDtoRequest) {

        var response = Optional.ofNullable(customerCreateDtoRequest)
            .map(this.mapperIn::toCustomerCreate)
            .map(this.customerCreateInputPort::create)
            .map(this.mapperIn::toCustomerCreateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/customers" + response.customerId()))
            .body(response);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<CustomerSearchDtoResponse>> search(final CustomerFilter customerFilter,
        @PageableDefault(sort = "customerId", direction = Sort.Direction.DESC, size = Constantes.PAGE_SIZE) final Pageable paginacao) {

        var response = Optional.ofNullable(customerFilter)
            .map(filter -> this.customerSearchOutputPort.search(filter, paginacao))
            .orElseThrow();

        return ResponseEntity
            .ok()
            .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") @Positive final Long customerId) {

        Optional.ofNullable(customerId)
            .ifPresentOrElse(this.customerDeleteInputPort::delete,
                () -> {throw new NoSuchElementException();}
            );

        return ResponseEntity
            .noContent()
            .build();
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerUpdateDtoResponse> update(@Valid @RequestBody CustomerUpdateDtoRequest customerUpdateDtoRequest) {

        var response = Optional.ofNullable(customerUpdateDtoRequest)
            .map(this.mapperIn::toCustomerUpdate)
            .map(this.customerUpdateInputPort::update)
            .map(this.mapperIn::toCustomerUpdateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .ok()
            .body(response);
    }

}

