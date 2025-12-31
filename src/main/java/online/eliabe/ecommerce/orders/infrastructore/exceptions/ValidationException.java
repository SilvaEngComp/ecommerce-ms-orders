package online.eliabe.ecommerce.orders.infrastructore.exceptions;

import feign.FeignException;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private String field;
    private String message;
    private FeignException errorClass;

    public ValidationException(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

    public ValidationException(String field, String message, FeignException errorClass) {
        this.field = field;
        this.message = message;
        this.errorClass = errorClass;
    }
}
