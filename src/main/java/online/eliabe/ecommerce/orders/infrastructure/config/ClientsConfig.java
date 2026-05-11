package online.eliabe.ecommerce.orders.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "online.eliabe.ecommerce.orders.infrastructore.externalServices")
public class ClientsConfig {
}
