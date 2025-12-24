package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderRepository;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgresOrderRepository implements OrderOutputPort {
    private final OrderMapper mapper;
    private final OrderRepository repository;
    @Override
    public OrderResponseDTO save(OrderRequestDTO requestDTO) {
        return Optional.of(requestDTO)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDTO)
                .orElseThrow();
    }

    @Override
    public Optional<OrderResponseDTO> findByCode(Long code) {
        return repository.findById(code).map(mapper::toDTO);
    }
}
