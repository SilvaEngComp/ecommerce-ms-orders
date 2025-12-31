package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.infrastructore.externalServices.BankClientManagerService;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderItemRepository;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderRepository;
import online.eliabe.ecommerce.orders.infrastructore.validator.ValidatorOrderManager;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgresOrderRepository implements OrderOutputPort {
    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final OrderItemRepository orderItemrepository;
    private final ValidatorOrderManager validator;
    private final BankClientManagerService bankClientManagerService;

    @Override
    @Transactional
    public OrderResponseDTO save(OrderRequestDTO requestDTO) {
        OrderEntity orderEntity = mapper.toEntity(requestDTO);
        validator.validate(orderEntity);
         return Optional.of(orderEntity)
                .map(this::registerOrder)
                 .map(bankClientManagerService::paymentRequest)
                .map(mapper::toDTO)
                .orElseThrow();


    }

    private @NonNull OrderEntity registerOrder(OrderEntity order) {
        repository.save(order);
        orderItemrepository.saveAll(order.getItens());
        return order;
    }

    @Override
    public Optional<OrderResponseDTO> findByCode(Long code) {
        return repository.findById(code).map(mapper::toDTO);
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
