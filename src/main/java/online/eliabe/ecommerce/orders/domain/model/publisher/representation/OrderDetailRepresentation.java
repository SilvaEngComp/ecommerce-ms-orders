package online.eliabe.ecommerce.orders.domain.model.publisher.representation;

import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailRepresentation(
        Long orderCode, Long clientCode, String name,
        String cpf, String street,
        String houseNumber, String district, String email,
        String phone, String orderDate,
        BigDecimal total, OrderStatus status,
        List<OrderItemDetailRepresentation> itens) {
}
