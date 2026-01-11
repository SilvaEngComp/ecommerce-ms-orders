package online.eliabe.ecommerce.orders.domain.service;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.*;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase, GetOrderUseCase, FindAllOrderUseCase, ReceivedPaymentCallbackUseCase, NewPaymentUseCase {
    private final OrderOutputPort outputPort;
    @Override
    public OrderResponseDTO execute(OrderRequestDTO request) {
        return outputPort.save(request);
    }

    @Override
    public Optional<OrderResponseDTO> execute(Long code) {
        return  outputPort.findByCode(code);
    }


    @Override
    public List<OrderResponseDTO> execute() {
        return outputPort.findAll();
    }

    @Override
    public void execute(Long code, String paymentKey, boolean status, String comments) {
         outputPort.updatePaymentStatus( code,  paymentKey,  status,  comments);
    }

    @Override
    public void execute(AddNewPaymentDTO addNewPaymentDTO) {
        outputPort.addNewPayment(addNewPaymentDTO);
    }
}
