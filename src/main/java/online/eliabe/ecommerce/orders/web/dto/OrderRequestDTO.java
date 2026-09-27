package online.eliabe.ecommerce.orders.web.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderRequestDTO(
        @Nullable Long code,
        @NotNull(message = "client code can not be null")
        Long clientCode,
        PaymentDataDTO paymentDataDTO,
        List<OrderItemDTO> itens) {
}