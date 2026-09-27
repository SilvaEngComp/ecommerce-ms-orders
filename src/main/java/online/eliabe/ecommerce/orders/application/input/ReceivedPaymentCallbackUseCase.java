package online.eliabe.ecommerce.orders.application.input;

import java.util.Optional;

@FunctionalInterface
public interface ReceivedPaymentCallbackUseCase {
    public void execute(Long code,String paymentKey,boolean status, String comments);
}
