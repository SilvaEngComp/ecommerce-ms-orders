package online.eliabe.ecommerce.orders.application.output;

import java.util.Optional;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;

public interface OrderPaymentStatusPort {
 public Optional<OrderEntity> findByCodeAndPaymentKey(Long code, String paymentKey);
}
