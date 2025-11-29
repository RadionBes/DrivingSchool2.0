package radion.ru.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import radion.ru.userservice.exception.exceptions.BadPasswordException;
import radion.ru.userservice.exception.exceptions.UserNotFoundException;

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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> userNotFoundByEmail(UserNotFoundException notFoundException){
        ExceptionResponse response = ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(notFoundException.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
