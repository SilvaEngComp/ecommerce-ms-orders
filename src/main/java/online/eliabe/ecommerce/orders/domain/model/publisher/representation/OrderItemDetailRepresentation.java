package online.eliabe.ecommerce.orders.domain.model.publisher.representation;

import java.math.BigDecimal;

public record OrderItemDetailRepresentation(Long codeProduct, String productName, Integer quantity, BigDecimal unitPrice) {

    public BigDecimal getTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
