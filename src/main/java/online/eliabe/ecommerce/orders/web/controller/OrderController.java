package online.eliabe.ecommerce.orders.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.domain.service.OrderService;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.web.swagger.OrderSwaggerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController implements OrderSwaggerController {
    private final OrderService service;
    @Operation(summary = "Find product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied",
                    content = @Content),
    })
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
}
