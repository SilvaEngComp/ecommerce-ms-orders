package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

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
}
