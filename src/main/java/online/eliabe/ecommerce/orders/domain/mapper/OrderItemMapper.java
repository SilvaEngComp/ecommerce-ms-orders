package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity entity);
    OrderEntity toEntity(OrderRequestDTO orderRequestDTO);
}
