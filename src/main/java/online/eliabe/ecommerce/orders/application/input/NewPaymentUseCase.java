package online.eliabe.ecommerce.orders.application.input;

import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;

@FunctionalInterface
public interface NewPaymentUseCase {
    public void execute(AddNewPaymentDTO addNewPaymentDTO);
}
