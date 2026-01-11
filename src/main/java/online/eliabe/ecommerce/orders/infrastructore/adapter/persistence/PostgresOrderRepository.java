package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.mapper.OrderMapper;
import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentData;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;
import online.eliabe.ecommerce.orders.infrastructore.exceptions.ItemNotFoundException;
import online.eliabe.ecommerce.orders.infrastructore.externalServices.BankClientManagerService;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderItemRepository;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.repository.OrderRepository;
import online.eliabe.ecommerce.orders.infrastructore.validator.ValidatorOrderManager;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    @Override
    @Transactional
    public void updatePaymentStatus(Long code, String paymentKey, boolean status, String comments) {
        try {
            var orderEntity = repository.findByCodeAndPaymentKey(code, paymentKey).orElseThrow();
            if (status) {
                orderEntity.setStatus(OrderStatus.PAYED);
            } else {
                orderEntity.setStatus(OrderStatus.PAYMENT_ERROR);
                orderEntity.setObservations(comments);
            }
        }catch (Exception e){
            var message = String.format("Order not found for code %d and payment key %s", code, paymentKey);
            log.error(message);
        }
    }

    @Transactional
    @Override
    public void addNewPayment(AddNewPaymentDTO addNewPaymentDTO){
        var orderFound = repository.findById(addNewPaymentDTO.orderCode());
        if(orderFound.isEmpty()){
            throw new ItemNotFoundException("This order was not found","orderCode");
        }

        var order = orderFound.get();

        PaymentData newPayment = new PaymentData();
        newPayment.setPaymentType(addNewPaymentDTO.paymentType());
        newPayment.setData(addNewPaymentDTO.data());

        order.setPaymentData(newPayment);
        order.setStatus(OrderStatus.SENT);
        order.setObservations("new payment made. Waiting for confirmation");

        order = bankClientManagerService.paymentRequest(order);

        repository.save(order);

    }
}
