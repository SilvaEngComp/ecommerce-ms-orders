package online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    Optional<OrderEntity> findByCodeAndPaymentKey(Long code, String paymentKey);

    List<OrderItemEntity> findByOrder(OrderEntity orderEntity);
}
