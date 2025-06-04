package kr.hhplus.be.server.infrastructure.kafka;

import kr.hhplus.be.server.domain.product.ProductOrderedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductOrderKafkaProducer {

    private final KafkaTemplate<String, ProductOrderedEvent> kafkaTemplate;

    @Value("product-ordered")
    private String topic;

    public void send(ProductOrderedEvent event) {
        kafkaTemplate.send(topic, event);
    }

}
