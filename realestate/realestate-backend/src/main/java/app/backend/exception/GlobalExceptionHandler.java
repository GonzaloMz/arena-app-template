package app.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import arena.backend.exception.ArenaException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RealestateException.class)
    public ResponseEntity<RealestateException> exception(RealestateException e) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
    }
    
    @ExceptionHandler(ArenaException.class)
    public ResponseEntity<ArenaException> exception(ArenaException e) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
    }
}
