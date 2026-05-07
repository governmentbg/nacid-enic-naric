package bg.duosoft.nacidshared.web.controller.advice;

import feign.FeignException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ResponseEntity<Object> validationErrorHandlerFeignException(FeignException e) {
        if (e.status() == HttpStatus.BAD_REQUEST.value()) {
            if (e.responseBody().isPresent()) {
                return new ResponseEntity<>(new String(e.responseBody().get().array()), HttpStatus.BAD_REQUEST);
            }
        }
        log.error("Error calling feign client", e);


        return new ResponseEntity<>(ResponseError.newInstance(), HttpStatus.valueOf(e.status()));
    }

    @Data
    @NoArgsConstructor(staticName = "newInstance")
    static class ResponseError {
        private String message = "Error occurred during the operation";
    }
}
