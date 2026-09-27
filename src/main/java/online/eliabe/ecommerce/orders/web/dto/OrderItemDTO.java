package online.eliabe.ecommerce.orders.web.dto;

public record OrderItemDTO(

        Long codeProduct,

        Integer quantity,

        Integer unitPrice) {

}
