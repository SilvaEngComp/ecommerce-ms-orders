package online.eliabe.ecommerce.orders.infrastructure.externalServices.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long code, String name, BigDecimal unitPrice) {
}
