package online.eliabe.ecommerce.orders.domain.useCase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.NewPaymentUseCase;
import online.eliabe.ecommerce.orders.application.output.OrderOutputPort;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;

@Service
@AllArgsConstructor
public class NewPaymentUseCaseImpl implements NewPaymentUseCase {
private final OrderOutputPort outputPort;

    @Override
    public void execute(AddNewPaymentDTO addNewPaymentDTO) {
        outputPort.addNewPayment(addNewPaymentDTO);
    }
}
