package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.ReceivedPaymentCallbackUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;

@Service
@AllArgsConstructor
public class ReceivedPaymentCallbackUseCaseImpl implements ReceivedPaymentCallbackUseCase {
    private final OrderOutputPort outputPort;
    @Override
    public void execute(Long code, String paymentKey, boolean status, String comments) {
       outputPort.updatePaymentStatus(code, paymentKey, status, comments);
    }

}
