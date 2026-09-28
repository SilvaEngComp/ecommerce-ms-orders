package online.eliabe.ecommerce.orders.domain.service;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.*;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;
import online.eliabe.ecommerce.orders.domain.useCase.CreateOrderUserCaseImpl;
import online.eliabe.ecommerce.orders.domain.useCase.FindAllOrderUseCaseImpl;
import online.eliabe.ecommerce.orders.domain.useCase.GetOrderUseCaseImpl;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderItemDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
   private final CreateOrderUserCaseImpl createOrderUserCase;
   private final GetOrderUseCaseImpl GetOrderUseCase;
   private final FindAllOrderUseCaseImpl findAllOrderUseCase;
   private final ReceivedPaymentCallbackUseCase receivedPaymentCallbackUseCase;
   private final NewPaymentUseCase newPaymentUseCase;
    
   @Override
   public OrderResponseDTO save(OrderRequestDTO request) {
        return createOrderUserCase.execute(request);
    }

    public Optional<OrderEntity> getOrderDetails(Long code) {
        return GetOrderUseCase.execute(code);
    }

    public List<OrderResponseDTO> findAll() {
        return findAllOrderUseCase.execute();
    }

    @Override
    public void updatePaymentStatus(Long code, String paymentKey, boolean status, String comments) {
         receivedPaymentCallbackUseCase.execute( code,  paymentKey,  status,  comments);
    }

    @Override
    public void addNewPayment(AddNewPaymentDTO addNewPaymentDTO) {
        newPaymentUseCase.execute(addNewPaymentDTO);
    }

}
