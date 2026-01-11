package online.eliabe.ecommerce.orders.application.output;

import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrderOutputPort {
    public OrderResponseDTO save(OrderRequestDTO requestDTO);
    public Optional<OrderResponseDTO> findByCode(Long code);
    public List<OrderResponseDTO> findAll();
    public void updatePaymentStatus(Long code,String paymentKey,boolean status, String comments);
}
