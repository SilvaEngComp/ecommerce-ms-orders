package online.eliabe.ecommerce.orders.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record OrderItemDTO(

        Long codeProduct,

        Integer quantity,

        Integer unitPrice) {

}
