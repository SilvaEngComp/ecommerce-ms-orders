package online.eliabe.ecommerce.orders.web.controller;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.IOrderService;
import online.eliabe.ecommerce.orders.infrastructure.exceptions.ItemNotFoundException;
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
    private final IOrderService service;
    @Override
    @PostMapping
    public ResponseEntity<Object> createOrder(OrderRequestDTO requestDTO) {
        var order = service.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.code());
    }

    @Override
    @GetMapping(value = "/{code}")
    public ResponseEntity<OrderResponseDTO> findByCode(Long code) {
        return service.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return  Optional.of(service.findAll()).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/payment")
    @Override
    public ResponseEntity<Object> newPayment(@RequestBody AddNewPaymentDTO dto) {
            service.newPayment(dto);
            return ResponseEntity.noContent().build();


    }
}
