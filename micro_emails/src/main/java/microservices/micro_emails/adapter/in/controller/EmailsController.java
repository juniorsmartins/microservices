package microservices.micro_emails.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_emails.adapter.dto.response.ContactInfoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "API Rest Micro_Emails.",
    description = "São disponibilizados os recursos: Contact-Information.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/emails")
@RequiredArgsConstructor
public class EmailsController {

    private final ContactInfoDtoResponse contactInfoDtoResponse;

    @GetMapping(path = "/contact-info")
    @Operation(summary = "Get Contact Information", description = "Buscar informações de contato do Micro_Emails.",
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

}

