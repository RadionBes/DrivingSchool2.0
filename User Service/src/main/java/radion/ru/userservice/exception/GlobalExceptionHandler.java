package radion.ru.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import radion.ru.userservice.exception.exceptions.BadPasswordException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> badPass(BadPasswordException badPasswordException){
        ExceptionResponse response = ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message(badPasswordException.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
