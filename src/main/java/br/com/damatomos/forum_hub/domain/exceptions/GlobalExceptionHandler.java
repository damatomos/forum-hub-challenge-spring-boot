package br.com.damatomos.forum_hub.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> forbidenException(ForbiddenException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<String> unprocessableEntityException(UnprocessableEntityException ex)
    {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseErrorsDTO>> methodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        var fields = ex.getFieldErrors();
        var result = fields.stream().map(ResponseErrorsDTO::new).toList();

        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<String> duplicateEntityException(DuplicateEntityException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException ex)
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException ex)
    {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException ex)
    {
        ex.printStackTrace(); // ou Logger
        return ResponseEntity.internalServerError().body("Erro interno no servidor");
    }

    public record ResponseErrorsDTO(String field, String message) {

        public ResponseErrorsDTO(FieldError fieldError)
        {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
