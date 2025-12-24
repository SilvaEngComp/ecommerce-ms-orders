package online.eliabe.ecommerce.orders.web.dto;

import online.eliabe.ecommerce.orders.domain.model.enums.PaymentType;

public record PaymentDataDTO (String dados, PaymentType paymentType) {
}
