package online.eliabe.ecommerce.orders.web.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.domain.service.OrderService;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.web.swagger.OrderSwaggerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController implements OrderSwaggerController {
    private final OrderService service;
    @Override
    public ResponseEntity<Object> createOrder(OrderRequestDTO requestDTO) {
        var order = service.execute(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.code());
    }

    @Override
    public ResponseEntity<OrderResponseDTO> findByCode(Long code) {
        return service.execute(code)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
