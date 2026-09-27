package online.eliabe.ecommerce.orders.web.controller;

import lombok.RequiredArgsConstructor;
import online.eliabe.ecommerce.orders.application.input.IOrderService;
import online.eliabe.ecommerce.orders.web.dto.ReceivedPaymentCallbackDTO;
import online.eliabe.ecommerce.orders.web.swagger.ReceivedPaymentCallbackSwaggerController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders/callback-payments")
@RequiredArgsConstructor
public class ReceivedPaymentCallbackController implements ReceivedPaymentCallbackSwaggerController {
    private final IOrderService service;


    @GetMapping
    @Override
    public ResponseEntity<Object> updatePaymentState(@RequestBody ReceivedPaymentCallbackDTO body, @RequestHeader(required = true, name = "apiKey") String apiKey) {
        service.updatePaymentStatus(body.code(),body.paymentKey(),body.status(),body.comments());
        return ResponseEntity.ok().build();
    }
}
