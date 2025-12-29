package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.output.OrderItemOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderItemMapper;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderItemRepository;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgresOrderItemRepository implements OrderItemOutputPort {
    private final OrderItemMapper mapper;
    private final OrderItemRepository repository;
    @Override
    public List<OrderItemEntity> saveAll(List<OrderItemEntity> itens) {
       return repository.saveAll(itens);

    }

    @Override
    public Optional<OrderResponseDTO> findByCode(Long code) {
        return repository.findById(code).map(mapper::toDTO);
    }

    @Override
    public List<OrderItemDTO> findAll(OrderRequestDTO requestDTO) {
        return List.of();
    }
}
