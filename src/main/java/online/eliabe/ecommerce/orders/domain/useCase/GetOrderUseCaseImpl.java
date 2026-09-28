package online.eliabe.ecommerce.orders.domain.useCase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.GetOrderUseCase;
import online.eliabe.ecommerce.orders.application.input.IPopulateOrderDetails;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.ExternalSrvClient;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.ExternalSrvProduct;

@Service
@AllArgsConstructor
public class GetOrderUseCaseImpl implements GetOrderUseCase {
    private final OrderOutputPort outputPort;
    private final IPopulateOrderDetails populateOrderDetails;

    @Override
    public Optional<OrderEntity> execute(Long code) {
        Optional<OrderEntity> order =   outputPort.findByCode(code);
        order.ifPresent(populateOrderDetails::getDataCliente);
        order.ifPresent(populateOrderDetails::getOrderItens);
        return order;
    }


}
