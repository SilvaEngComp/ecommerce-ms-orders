package online.eliabe.ecommerce.orders.infrastructore.externalServices;

import online.eliabe.ecommerce.orders.infrastructore.externalServices.representation.ProductRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "${ecommerce.orders.clients.products.url}")
public interface ExternalSrvProduct {

    @GetMapping("/{code}")
    ResponseEntity<ProductRepresentation> findByCode(@PathVariable("code") Long code);
}
