package online.eliabe.ecommerce.orders.domain.mapper;

import online.eliabe.ecommerce.orders.domain.model.publisher.representation.OrderDetailRepresentation;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OderDetailMapper {

    @Mapping(source = "code", target = "orderCode")
    @Mapping(source = "clientCode", target = "clientCode")
    @Mapping(source = "clientData.name", target = "name")
    @Mapping(source = "clientData.cpf", target = "cpf")
    @Mapping(source = "clientData.street", target = "street")
    @Mapping(source = "clientData.houseNumber", target = "houseNumber")
    @Mapping(source = "clientData.district", target = "district")
    @Mapping(source = "clientData.email", target = "email")
    @Mapping(source = "clientData.phone", target = "phone")
    @Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "itens", target = "itens")
    OrderDetailRepresentation map(OrderEntity entity);
}
