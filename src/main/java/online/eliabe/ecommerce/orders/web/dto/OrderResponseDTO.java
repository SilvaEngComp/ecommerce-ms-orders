package online.eliabe.ecommerce.orders.web.dto;

import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO (
        Long code,

        Long clientCode,


        LocalDateTime orderDate,

        BigDecimal total,

        String paymentKey,

        String observations,


        OrderStatus status,

        String trackCode,

        String urlNF, List<OrderItemDTO> itens){
}
