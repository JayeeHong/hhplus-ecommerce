package kr.hhplus.be.server.infrastructure.order;

import kr.hhplus.be.server.domain.product.ProductOrderedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductOrderConsumer {

    @KafkaListener(
        topics = "${kafka.topic.product-ordered}",
        groupId = "product-order-consumer-group",
        containerFactory = "productOrderKafkaListenerContainerFactory"
    )
    public void consume(ProductOrderedEvent event) {
        log.info("상품 주문 이벤트 수신: {}", event.getItems());
    }

}
