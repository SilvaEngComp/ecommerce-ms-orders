package online.eliabe.ecommerce.orders.web.handler;

import feign.FeignException;
import online.eliabe.ecommerce.orders.infrastructore.exceptions.ItemNotFoundException;
import online.eliabe.ecommerce.orders.infrastructore.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ValidationException ex) {
        var httpStatus = checkHttpStatus(ex.getErrorClass());
        return ResponseEntity.status(httpStatus)
                .body(new ErrorResponse(ex.getMessage(),ex.getField(), ex.getErrorClass().getMessage()));
    }


    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(),ex.getField(), ex.getMessage()));
    }

    private HttpStatus checkHttpStatus(FeignException errorClass){
        if(errorClass instanceof FeignException.NotFound){
            return HttpStatus.NOT_FOUND;
        }
            return HttpStatus.BAD_GATEWAY;

    }
}
