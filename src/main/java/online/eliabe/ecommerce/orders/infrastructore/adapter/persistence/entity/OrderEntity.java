package online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.eliabe.ecommerce.orders.domain.model.enums.OrderStatus;
import online.eliabe.ecommerce.orders.domain.model.enums.PaymentData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "client_code", nullable = false)
    private Long clientCode;


    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total", precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "observations")
    private String observations;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "track_code", length = 255)
    private String trackCode;

    @Column(name = "url_nf")
    private String urlNF;

    @Transient
    private PaymentData paymentData;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> itens;
}
