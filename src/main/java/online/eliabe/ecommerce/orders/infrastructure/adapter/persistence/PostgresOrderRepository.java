package online.eliabe.ecommerce.orders.infrastructure.adapter.persistence;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.application.output.OrderPaymentStatusPort;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.repository.OrderItemRepository;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.repository.OrderRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgresOrderRepository implements OrderOutputPort, OrderPaymentStatusPort {
    private final OrderRepository repository;
    private final OrderItemRepository orderItemrepository;

    @Override
    public Optional<OrderEntity> save(OrderEntity orderEntity) {
        return Optional.of(orderEntity)
                .map(this::registerOrder);
    }

    private @NonNull OrderEntity registerOrder(OrderEntity order) {
        repository.save(order);
        orderItemrepository.saveAll(order.getItens());
        return order;
    }

    @Override
    public Optional<OrderEntity> findByCode(Long code) {
        return repository.findById(code);
    }

    @Override
    public List<OrderEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<OrderItemEntity> findByOrder(OrderEntity orderEntity) {
        return this.orderItemrepository.findByOrder(orderEntity);
    }

    @Override
    public Optional<OrderEntity> findByCodeAndPaymentKey(Long code, String paymentKey) {
        return repository.findByCodeAndPaymentKey(code, paymentKey);
    }
    

}
