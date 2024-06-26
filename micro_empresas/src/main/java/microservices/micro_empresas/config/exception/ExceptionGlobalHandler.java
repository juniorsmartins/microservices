package microservices.micro_empresas.config.exception;

import lombok.RequiredArgsConstructor;
import microservices.micro_empresas.config.exception.http_404.ResourceNotFoundException;
import microservices.micro_empresas.config.exception.http_409.BusinessRuleViolationException;
import microservices.micro_empresas.config.exception.http_500.InternalServerFailureException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public final class ExceptionGlobalHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    // ---------- PARA CAPITURAR TODAS AS EXCEÇÕES SEM TRATAMENTO ESPECÍFICO ---------- //
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/erro-interno-servidor"));
        problemDetail.setTitle(this.getMessage("exception.internal.server.error"));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(problemDetail);
    }

    // ---------- TRATAMENTO DE EXCEÇÕES DEFAULT ---------- //
    // ---------- Sobreescrever método de ResponseEntityExceptionHandler para customizar ---------- //
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatusCode httpStatusCode,
                                                                  WebRequest webRequest) {
        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatusCode);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/campos-invalidos"));
        problemDetail.setTitle(this.getMessage("exception.resources.campos.invalidos"));

        var fields = this.getFields(ex);

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, httpHeaders, httpStatusCode, webRequest);
    }

    // ---------- Métodos assessórios ---------- //
    private String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, new Object[]{}, LocaleContextHolder.getLocale());
    }

    private Map<String, String> getFields(BindException ex) {
        return ex.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                objectError -> this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
    }


    // ---------- TRATAMENTO DE EXCEÇÕES CUSTOM ---------- //
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex, WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/recurso-nao-encontrado"));

        var id = ex.getId();

        var mensagem = this.messageSource.getMessage(ex.getMessageKey(), new Object[]{id},
            LocaleContextHolder.getLocale());

        problemDetail.setTitle(String.format(mensagem, id));

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problemDetail);
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ProblemDetail> handleBusinessRuleViolation(BusinessRuleViolationException ex,
                                                                     WebRequest webRequest) {
        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/regras-de-negocio-violadas"));
        problemDetail.setTitle(this.getMessage(ex.getMessageKey()));

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(problemDetail);
    }

    @ExceptionHandler(InternalServerFailureException.class)
    public ResponseEntity<ProblemDetail> handleInternalServerFailure(InternalServerFailureException ex,
                                                                     WebRequest webRequest) {
        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/erro-interno-servidor"));
        problemDetail.setTitle(this.getMessage(ex.getMessageKey()));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(problemDetail);
    }

}

