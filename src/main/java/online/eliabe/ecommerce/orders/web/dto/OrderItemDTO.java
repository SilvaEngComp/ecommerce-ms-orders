package online.eliabe.ecommerce.orders.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record OrderItemDTO(
                @NotNull Long codeProduct,
                @Positive Integer quantity,
                @Positive Integer unitPrice) {
}