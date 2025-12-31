package online.eliabe.ecommerce.orders.web.handler;

public record ErrorResponse(String message, String field, String error) {
}
