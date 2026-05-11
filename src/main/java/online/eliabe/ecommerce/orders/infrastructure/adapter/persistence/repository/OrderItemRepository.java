package online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {
}
