package online.eliabe.ecommerce.orders.web.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import online.eliabe.ecommerce.orders.domain.service.OrderService;
import online.eliabe.ecommerce.orders.web.dto.AddNewPaymentDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation with success"),
        @ApiResponse(responseCode = "400", description = "Invalid parameter",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

})
public interface OrderSwaggerController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created with success"),
    })
    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDTO requestDTO);


    @GetMapping("/{code}")
    @Operation(summary = "Find order by Code")
    public ResponseEntity<OrderResponseDTO> findByCode(@PathVariable Long code);


    @GetMapping
    @Operation(summary = "Find all orders")
    ResponseEntity<List<OrderResponseDTO>> findAll();

    @PostMapping("/payment")
    @Operation(summary = "Add a new payment")
    public ResponseEntity<Object> newPayment(@RequestBody AddNewPaymentDTO dto) ;
}
