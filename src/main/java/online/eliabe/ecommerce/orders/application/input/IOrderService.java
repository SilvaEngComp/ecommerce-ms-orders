package online.eliabe.ecommerce.orders.application.input;

import java.util.List;
import java.util.Optional;

import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;

public interface IOrderService {
    public OrderResponseDTO save(OrderRequestDTO request);

    public Optional<OrderResponseDTO> findByCode(Long code);

    public List<OrderResponseDTO> findAll();

    public void updatePaymentStatus(Long code, String paymentKey, boolean status, String comments);

    public void addNewPayment(AddNewPaymentDTO addNewPaymentDTO);
}
