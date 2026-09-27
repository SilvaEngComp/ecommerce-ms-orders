package online.eliabe.ecommerce.orders.domain.useCase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.GetOrderUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.ExternalSrvClient;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

@Service
@AllArgsConstructor
public class GetOrderUseCaseImpl implements GetOrderUseCase {
    private final OrderOutputPort outputPort;
    private final OrderMapper mapper;
    private final ExternalSrvClient externalServiceClient;
    @Override
    public Optional<OrderResponseDTO> execute(Long code) {
        Optional<OrderEntity> order =   outputPort.findByCode(code);
        order.ifPresent(this::getDataCliente);
        order.ifPresent(this::getOrderItens);
        return order.map(mapper::toDTO);
    }

    private void getDataCliente(OrderEntity orderEntity) {
        Long clientCode = orderEntity.getClientCode();
        var response = externalServiceClient.findByCode(clientCode);
        orderEntity.setClientData(response.getBody());
    }

    private void getOrderItens(OrderEntity orderEntity) {
        List<OrderItemEntity> orderItems = outputPort.findByOrder(orderEntity);
        orderEntity.setItens(orderItems);
    }

}
