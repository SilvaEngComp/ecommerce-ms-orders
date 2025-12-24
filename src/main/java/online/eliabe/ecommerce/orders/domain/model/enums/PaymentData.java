package online.eliabe.ecommerce.orders.domain.model.enums;

import lombok.Data;

@Data
public class PaymentData {
    private String data;
    private PaymentType paymentType;
}
