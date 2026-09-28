package online.eliabe.ecommerce.orders.domain.model.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.eliabe.ecommerce.orders.domain.mapper.OrderDetailMapper;
import online.eliabe.ecommerce.orders.infrastructure.adapter.persistence.entity.OrderEntity;

@Slf4j 
@Component 
@RequiredArgsConstructor 
public class PaymentPublisher {
private final OrderDetailMapper orderDetailMapper;
private final ObjectMapper objectMapper;
private final KafkaTemplate<String, String> kafkaTemplate;

@Value("${ecommerce.config.kafka.topics.payed-orders}")
private String paymentTopic;

public void publish(OrderEntity orderEntity) {
  log.info("Publishing order with code {} to topic {}", orderEntity.getCode(), paymentTopic);
  try { 
    var orderDetailRepresentation = orderDetailMapper.map(orderEntity);
    var message = objectMapper.writeValueAsString(orderDetailRepresentation);
    kafkaTemplate.send(paymentTopic, message);
    log.info("Order with code {} published successfully to topic {}", orderEntity.getCode(), paymentTopic);
  } catch (JsonProcessingException e) {
    log.error("Failed to process JSON order with code {} to topic {}: {}", orderEntity.getCode(), paymentTopic, e.getMessage());
  }catch(RuntimeException e) {
    log.error("Failed to publish order with code {} to topic {}: {}", orderEntity.getCode(), paymentTopic, e.getMessage());
  }
}
}
