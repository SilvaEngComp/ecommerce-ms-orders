package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

import java.util.Optional;

@FunctionalInterface
public interface GetOrderUseCase {
    public Optional<OrderResponseDTO> execute(Long code);
}
