package online.eliabe.ecommerce.orders.web.dto;

public record ReceivedPaymentCallbackDTO(Long code,String paymentKey,boolean status, String comments) {
}
