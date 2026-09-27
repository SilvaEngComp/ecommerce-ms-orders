package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity entity);

    @Mapping(source = "productCode", target = "productCode")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "unitPrice", target = "unitPrice")
    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
}
