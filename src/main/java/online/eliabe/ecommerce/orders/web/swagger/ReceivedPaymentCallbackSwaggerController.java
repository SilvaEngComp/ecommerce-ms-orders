package online.eliabe.ecommerce.orders.web.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.web.dto.ReceivedPaymentCallbackDTO;
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
public interface ReceivedPaymentCallbackSwaggerController {

    @GetMapping
    @Operation(summary = "Find all orders")
    public ResponseEntity<Object> updatePaymentState(@RequestBody ReceivedPaymentCallbackDTO body, @RequestHeader(required = true, name = "apiKey") String apiKey) ;
}
