package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Data
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "code_order", nullable = false)
    private Long codeOrder;

    @Column(name = "code_product", nullable = false)
    private Long codeProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 16, scale = 2)
    private BigDecimal unitPrice;
}
