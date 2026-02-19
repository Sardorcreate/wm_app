package sardorcreate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExists(AlreadyExistsException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "timestamp",
                        LocalDateTime.now(),
                        "status", 400,
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(NotExistsException.class)
    public ResponseEntity<?> handleNotExists(NotExistsException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "timestamp",
                        LocalDateTime.now(),
                        "status", 400,
                        "message", e.getMessage()
                ));
    }
}
