package online.eliabe.ecommerce.orders.domain.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.FindAllOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

@Service
@AllArgsConstructor
public class FindAllOrderUseCaseImpl implements FindAllOrderUseCase {
private final OrderOutputPort outputPort;
private final OrderMapper mapper;
    @Override
    public List<OrderResponseDTO>  execute() {
        return outputPort.findAll().stream().map(mapper::toDTO).toList();
    }

}
