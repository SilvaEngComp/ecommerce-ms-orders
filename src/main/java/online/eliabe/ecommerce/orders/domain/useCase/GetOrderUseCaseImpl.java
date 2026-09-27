package online.eliabe.ecommerce.orders.domain.useCase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.GetOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

@Service
@AllArgsConstructor
public class GetOrderUseCaseImpl implements GetOrderUseCase {
    private final OrderOutputPort outputPort;
    @Override
    public Optional<OrderResponseDTO> execute(Long code) {
        return  outputPort.findByCode(code);
    }

}
