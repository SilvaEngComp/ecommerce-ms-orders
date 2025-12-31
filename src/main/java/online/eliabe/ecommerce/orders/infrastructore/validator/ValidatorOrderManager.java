package online.eliabe.ecommerce.orders.infrastructore.validator;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderEntity;
import online.eliabe.ecommerce.orders.infrastructore.adapter.persistence.entity.OrderItemEntity;
import online.eliabe.ecommerce.orders.infrastructore.exceptions.ValidationException;
import online.eliabe.ecommerce.orders.infrastructore.externalServices.ExternalSrvClient;
import online.eliabe.ecommerce.orders.infrastructore.externalServices.ExternalSrvProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidatorOrderManager{
    private final ExternalSrvClient externalSrvClient;
    private final ExternalSrvProduct externalSrvProduct;
    public OrderEntity validate(OrderEntity orderEntity){
        this.validateClient(orderEntity.getClientCode());
        List<Long> productCodes = orderEntity.getItens().stream().map(OrderItemEntity::getCodeProduct).toList();
        productCodes.forEach(this::validateProduct);
        return orderEntity;
    }

    private void validateClient(Long clientCode){
        try {
            var response = externalSrvClient.findByCode(clientCode);
            var client = response.getBody();
            log.info("client exists - code = {}, name = {}", client.code(), client.name());
        }catch(FeignException.NotFound e){
            log.info("client code = {} not found", clientCode, e);
            var message = String.format("client with code %d not found",clientCode);
            throw new ValidationException("clientCode",message, e);
        }catch (FeignException ex){
            log.info("Error: ", ex.getMessage());
            throw new ValidationException("clientCode",ex.getMessage(),ex);
        }
    }

    private void validateProduct(Long productCode){
        try{
            var response = externalSrvProduct.findByCode(productCode);
            var product = response.getBody();
            log.info("product exists - code = {}, name = {}", product.code(), product.name());
        }catch (FeignException.NotFound e){
            log.info("product code = {} not found", productCode);
            var message = String.format("product with code %d not found",productCode);
            throw new ValidationException("codeProduct",message, e);
        }catch (FeignException ex){
            log.info("Error: ", ex.getMessage());
            throw new ValidationException("productCode",ex.getMessage(),ex);
        }
    }
}
