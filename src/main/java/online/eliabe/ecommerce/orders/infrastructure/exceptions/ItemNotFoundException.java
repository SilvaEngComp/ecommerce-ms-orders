package online.eliabe.ecommerce.orders.infrastructure.exceptions;

import feign.FeignException;
import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException{
    private String field;
    private String message;

    public ItemNotFoundException(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

}
