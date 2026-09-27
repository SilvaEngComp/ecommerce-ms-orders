package online.eliabe.ecommerce.orders.application.output;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import java.util.List;
import java.util.Optional;

public interface OrderOutputPort {
    public Optional<OrderEntity> save(OrderEntity orderEntity);
    public Optional<OrderEntity> findByCode(Long code);
    public List<OrderEntity> findAll();
    public List<OrderItemEntity> findByOrder(OrderEntity orderEntity);
   
    
}
