package online.eliabe.ecommerce.orders.infrastructore.externalServices.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long code, String name, BigDecimal unitPrice) {
}
