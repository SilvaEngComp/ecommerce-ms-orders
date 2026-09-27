package online.eliabe.ecommerce.orders.infrastructure.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import online.eliabe.ecommerce.orders.infrastructure.externalServices.representation.ClientRepresentation;
import online.eliabe.ecommerce.orders.infrastructure.externalServices.representation.ProductRepresentation;

@FeignClient(name = "clients", url = "${ecommerce.orders.clients.clients.url}")
public interface ExternalSrvClient {

    @GetMapping("/{code}")
    ResponseEntity<ClientRepresentation> findByCode(@PathVariable("code") Long code);
}
