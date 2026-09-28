package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import java.util.Optional;

@FunctionalInterface
public interface GetOrderUseCase {
    public Optional<OrderEntity> execute(Long code);
}
