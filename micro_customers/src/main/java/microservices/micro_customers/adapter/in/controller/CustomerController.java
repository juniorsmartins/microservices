package microservices.micro_customers.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.request.CustomerUpdateDtoRequest;
import microservices.micro_customers.adapter.dto.response.ContactInfoDtoResponse;
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
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(
    name = "CRUD da API Rest Customer.",
    description = "São disponibilizados os recursos: Create (criar), Search (pesquisar), Update (atualizar) e Delete (apagar).")
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

    private final ContactInfoDtoResponse contactInfoDtoResponse;

    @GetMapping(path = "/contact-info")
    @Operation(summary = "Get Contact Information", description = "Buscar informações de contato do Micro_Customers.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.")
        }
    )
    public ResponseEntity<ContactInfoDtoResponse> getContactInfo() {
        return ResponseEntity
            .ok()
            .body(contactInfoDtoResponse);
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Create", description = "Recurso para criar (Create) novo Customer.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created - recurso cadastrado com sucesso.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerCreateDtoResponse.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = CustomerCreateDtoResponse.class)),}
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "409", description = "Conflict - violação de regras de negócio.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            )
        }
    )
    public ResponseEntity<CustomerCreateDtoResponse> create(
        @Parameter(name = "customerCreateDtoRequest", description = "Dados para cadastrar novo Customer.", required = true)
        @Valid @RequestBody CustomerCreateDtoRequest customerCreateDtoRequest) {

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
    @Operation(summary = "Search", description = "Recurso para pesquisar (Search) Customer por customerId, nomeCompleto, cpf, statusCadastro e email.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 0, schema = @Schema(implementation = CustomerSearchDtoResponse.class))),
                    @Content(mediaType = "application/xml", array = @ArraySchema(minItems = 0, schema = @Schema(implementation = CustomerSearchDtoResponse.class))),}
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            )
        }
    )
    public ResponseEntity<Page<CustomerSearchDtoResponse>> search(
        @Parameter(name = "customerFilter", description = "Parâmetros para pesquisar no banco de dados.", required = false)
        final CustomerFilter customerFilter,
        @PageableDefault(sort = "customerId", direction = Sort.Direction.DESC, size = Constantes.PAGE_SIZE)
        final Pageable paginacao) {

        var response = Optional.ofNullable(customerFilter)
            .map(filter -> this.customerSearchOutputPort.search(filter, paginacao))
            .orElseThrow();

        return ResponseEntity
            .ok()
            .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    @Operation(summary = "Delete", description = "Recurso para apagar (Delete) Customer.",
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content - requisição bem sucedida e sem retorno.",
                content = {@Content(mediaType = "application/json"), @Content(mediaType = "application/xml"),}
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "409", description = "Conflict - violação de regras de negócio.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            )
        }
    )
    public ResponseEntity<Void> deleteById(
        @Parameter(name = "customerId", description = "Identificador do Customer no banco de dados.", example = "22", required = true)
        @PathVariable(name = "id") @Positive final Long customerId) {

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
    @Operation(summary = "Update", description = "Recurso para atualizar (Update) Customer.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerUpdateDtoResponse.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = CustomerUpdateDtoResponse.class)),}
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "409", description = "Conflict - violação de regras de negócio.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProblemDetail.class))}
            )
        }
    )
    public ResponseEntity<CustomerUpdateDtoResponse> update(
        @Parameter(name = "customerUpdateDtoRequest", description = "Dados para atualizar Customer.", required = true)
        @Valid @RequestBody CustomerUpdateDtoRequest customerUpdateDtoRequest) {

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

