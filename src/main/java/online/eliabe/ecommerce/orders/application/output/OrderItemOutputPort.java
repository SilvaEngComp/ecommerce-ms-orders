package online.eliabe.ecommerce.orders.application.output;

import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrderItemOutputPort {
    public List<OrderItemEntity> saveAll(List<OrderItemEntity> itens);
    public Optional<OrderResponseDTO> findByCode(Long code);
    public List<OrderItemDTO> findAll(OrderRequestDTO orderRequestDTO);
}
