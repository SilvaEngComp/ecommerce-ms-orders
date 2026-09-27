package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.CreateOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

@Service
@AllArgsConstructor
public class CreateOrderUserCaseImpl implements CreateOrderUseCase {
 private final OrderOutputPort outputPort;
    @Override
    public OrderResponseDTO execute(OrderRequestDTO request) {
        return outputPort.save(request);
    }

}
