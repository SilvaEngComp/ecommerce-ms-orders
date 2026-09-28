package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity entity);

    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
}
