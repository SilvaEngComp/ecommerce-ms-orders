package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

@FunctionalInterface
public interface CreateOrderUseCase {
    public OrderResponseDTO execute(OrderRequestDTO request);
}
