package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.eliabe.ecommerce.orders.application.input.IPopulateOrderDetails;
import online.eliabe.ecommerce.orders.application.input.ReceivedPaymentCallbackUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.application.output.OrderPaymentStatusPort;
import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;
import online.eliabe.ecommerce.orders.domain.model.publisher.PaymentPublisher;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;

@Service
@AllArgsConstructor
@Slf4j
public class ReceivedPaymentCallbackUseCaseImpl implements ReceivedPaymentCallbackUseCase {
    private final OrderOutputPort outputPort;
    private final OrderPaymentStatusPort orderPaymentStatusPort;
    private final PaymentPublisher paymentPublisher;
    private final IPopulateOrderDetails populateOrderDetails;
    @Override
    public void execute(Long code, String paymentKey, boolean status, String comments) {
            var orderEntity = orderPaymentStatusPort.findByCodeAndPaymentKey(code, paymentKey).orElseThrow(() -> new IllegalArgumentException(
                        "Order not found for code " + code + " and payment key " + paymentKey));
            if (status) {
                prepareAndPublishAsPayed(orderEntity);
            } else {
                orderEntity.setStatus(OrderStatus.PAYMENT_ERROR);
                orderEntity.setObservations(comments);
            }
      outputPort.save(orderEntity);
    }
    
    private void prepareAndPublishAsPayed(OrderEntity orderEntity) {
        orderEntity.setStatus(OrderStatus.PAYED);
        populateOrderDetails.getDataCliente(orderEntity);
        populateOrderDetails.getOrderItens(orderEntity);
        paymentPublisher.publish(orderEntity);
    }

}
