package online.eliabe.ecommerce.orders.web.controller;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.domain.service.OrderService;
import online.eliabe.ecommerce.orders.web.dto.OrderRequestDTO;
import online.eliabe.ecommerce.orders.web.dto.OrderResponseDTO;
import online.eliabe.ecommerce.orders.web.dto.ReceivedPaymentCallbackDTO;
import online.eliabe.ecommerce.orders.web.swagger.OrderSwaggerController;
import online.eliabe.ecommerce.orders.web.swagger.ReceivedPaymentCallbackSwaggerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders/callback-payments")
@RequiredArgsConstructor
public class ReceivedPaymentCallbackController implements ReceivedPaymentCallbackSwaggerController {
    private final OrderService service;


    @GetMapping
    @Override
    public ResponseEntity<Object> updatePaymentState(@RequestBody ReceivedPaymentCallbackDTO body, @RequestHeader(required = true, name = "apiKey") String apiKey) {
        service.execute(body.code(),body.paymentKey(),body.status(),body.comments());
        return ResponseEntity.ok().build();
    }
}
