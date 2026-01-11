package online.eliabe.ecommerce.orders.web.dto;

import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;

public record AddNewPaymentDTO(Long orderCode, String data, PaymentType paymentType) {
}
