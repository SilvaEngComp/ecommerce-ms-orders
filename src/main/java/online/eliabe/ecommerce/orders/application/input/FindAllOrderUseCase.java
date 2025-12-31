package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

import java.util.List;

@FunctionalInterface
public interface FindAllOrderUseCase {
    public List<OrderResponseDTO> execute();
}
