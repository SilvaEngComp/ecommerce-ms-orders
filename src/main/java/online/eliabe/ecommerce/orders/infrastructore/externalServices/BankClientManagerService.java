package online.eliabe.ecommerce.orders.infrastructore.externalServices;
import lombok.extern.slf4j.Slf4j;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BankClientManagerService {

    public OrderEntity paymentRequest(OrderEntity orderEntity){
        log.info("Requesting payment to the order {}", orderEntity.getCode());
        orderEntity.setPaymentKey(UUID.randomUUID().toString());
        return orderEntity;
    }
}
