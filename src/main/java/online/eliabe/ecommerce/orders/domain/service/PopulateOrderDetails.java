package online.eliabe.ecommerce.orders.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.IPopulateOrderDetails;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.ExternalSrvClient;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.ExternalSrvProduct;

@Component
@AllArgsConstructor
public class PopulateOrderDetails implements IPopulateOrderDetails {
    private final ExternalSrvClient externalServiceClient;
    private final ExternalSrvProduct externalSrvProduct;
    private final OrderOutputPort outputPort;

    public void getDataCliente(OrderEntity orderEntity) {
        Long clientCode = orderEntity.getClientCode();
        var response = externalServiceClient.findByCode(clientCode);
        orderEntity.setClientData(response.getBody());
    }

    public void getOrderItens(OrderEntity orderEntity) {
        List<OrderItemEntity> orderItems = outputPort.findByOrder(orderEntity);
        orderEntity.setItens(orderItems);
        orderEntity.getItens().forEach(this::getProductData);
    }

    public void getProductData(OrderItemEntity item) {
        var productResponse = externalSrvProduct.findByCode(item.getCodeProduct());
        item.setProductName(productResponse.getBody().name());
    }
}
