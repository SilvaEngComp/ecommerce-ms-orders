package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

    public OrderResponseDTO toDTO(OrderEntity entity);

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
    OrderEntity toEntity(OrderRequestDTO requestDTO);

    @Named("mapItens")
    default List<OrderItemEntity> mapItens(List<OrderItemDTO> orderItensDTO){
        return orderItensDTO.stream().map(ORDER_ITEM_MAPPER::toEntity).toList();
    }

    @AfterMapping
    default void afterMapping(@MappingTarget OrderEntity orderEntity){
        orderEntity.setStatus(OrderStatus.REQUESTED);
        orderEntity.setOrderDate(LocalDateTime.now());

        orderEntity.setTotal(calcTotal(orderEntity));
    }

    private static BigDecimal calcTotal(OrderEntity orderEntity) {
        return orderEntity.getItens().stream()
                .map(item->item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
