package online.eliabe.ecommerce.orders.web.controller;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.domain.service.OrderService;
import online.eliabe.ecommerce.orders.infrastructore.exceptions.ItemNotFoundException;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.web.swagger.OrderSwaggerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController implements OrderSwaggerController {
    private final OrderService service;
    @Override
    @PostMapping
    public ResponseEntity<Object> createOrder(OrderRequestDTO requestDTO) {
        var order = service.execute(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.code());
    }

    @Override
    @GetMapping(value = "/{code}")
    public ResponseEntity<OrderResponseDTO> findByCode(Long code) {
        return service.execute(code)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return  Optional.of(service.execute()).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/payment")
    @Override
    public ResponseEntity<Object> newPayment(@RequestBody AddNewPaymentDTO dto) {
            service.execute(dto);
            return ResponseEntity.noContent().build();


    }
}
