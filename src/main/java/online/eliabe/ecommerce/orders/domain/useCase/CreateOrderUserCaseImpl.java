package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.CreateOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.validator.ValidatorOrderManager;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.BankClientManagerService;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateOrderUserCaseImpl implements CreateOrderUseCase {
    private final OrderOutputPort outputPort;
    private final OrderMapper mapper;
    private final ValidatorOrderManager validator;
    private final BankClientManagerService bankClientManagerService;

    @Override
    @Transactional
    public OrderResponseDTO execute(OrderRequestDTO request) {
        OrderEntity orderEntity = mapper.toEntity(request);
        validator.validate(orderEntity);
        return outputPort.save(orderEntity)
                .map(bankClientManagerService::paymentRequest)
                .map(mapper::toDTO)
                .orElseThrow();
    }

}
