package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.NewPaymentUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentData;
import online.eliabe.ecommerce.orders.infrastructure.exceptions.ItemNotFoundException;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.BankClientManagerService;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;

@Service
@AllArgsConstructor
public class NewPaymentUseCaseImpl implements NewPaymentUseCase {
private final OrderOutputPort outputPort;
private final BankClientManagerService bankClientManagerService;

    @Override
    @Transactional
    public void execute(AddNewPaymentDTO addNewPaymentDTO) {
        var orderFound = outputPort.findByCode(addNewPaymentDTO.orderCode());
        if(orderFound.isEmpty()){
            throw new ItemNotFoundException("This order was not found","orderCode");
        }

        var order = orderFound.get();

        PaymentData newPayment = new PaymentData();
        newPayment.setPaymentType(addNewPaymentDTO.paymentType());
        newPayment.setData(addNewPaymentDTO.data());

        order.setPaymentData(newPayment);
        order.setStatus(OrderStatus.REQUESTED);
        order.setObservations("new payment made. Waiting for confirmation");

        order = bankClientManagerService.paymentRequest(order);

        outputPort.save(order);
    }
}
