package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;

public interface IPopulateOrderDetails {
  public void getDataCliente(OrderEntity orderEntity);

   public  void getOrderItens(OrderEntity orderEntity);

    public void getProductData(OrderItemEntity item);
}
