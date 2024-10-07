package FirstProject.demo.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(PersonNotFoundException ex) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>("Product not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
