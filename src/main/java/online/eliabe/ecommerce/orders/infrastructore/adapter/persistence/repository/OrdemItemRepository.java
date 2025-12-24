package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository;

import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemItemRepository extends JpaRepository<OrderItemEntity,Long> {
}
