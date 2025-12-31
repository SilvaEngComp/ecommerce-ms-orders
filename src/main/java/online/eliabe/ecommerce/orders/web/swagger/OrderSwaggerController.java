package online.eliabe.ecommerce.orders.web.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
