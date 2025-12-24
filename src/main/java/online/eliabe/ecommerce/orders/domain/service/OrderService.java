package online.eliabe.ecommerce.orders.domain.service;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.CreateOrderUseCase;
import online.eliabe.ecommerce.orders.application.input.GetOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {
    private final OrderOutputPort outputPort;
    @Override
    public OrderResponseDTO execute(OrderRequestDTO request) {
        return outputPort.save(request);
    }

    @Override
    public Optional<OrderResponseDTO> execute(Long code) {
        return  outputPort.findByCode(code);
    }
}
