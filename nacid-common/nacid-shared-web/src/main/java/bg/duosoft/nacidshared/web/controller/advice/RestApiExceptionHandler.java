package bg.duosoft.nacidshared.web.controller.advice;

import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrorException(ValidationErrorException ex, WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        log.error("[400 BAD_REQUEST [{} {}] {}", servletRequest.getMethod(), servletRequest.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}