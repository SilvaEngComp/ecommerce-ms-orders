package online.eliabe.ecommerce.orders.web.dto;

import jakarta.validation.constraints.NotNull;
import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(
        @NotNull(message = "client code can not be null")
        Long clientCode,
        PaymentDataDTO paymentDataDTO,
        List<OrderItemDTO> itens) {
}