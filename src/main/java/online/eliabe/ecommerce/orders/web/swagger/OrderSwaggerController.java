package online.eliabe.ecommerce.orders.web.swagger;

import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
public interface OrderSwaggerController {



    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDTO requestDTO);

    @GetMapping("/{code}")
    public ResponseEntity<OrderResponseDTO> findByCode(@PathVariable Long code);

    @GetMapping
    ResponseEntity<List<OrderResponseDTO>> findAll();
}
