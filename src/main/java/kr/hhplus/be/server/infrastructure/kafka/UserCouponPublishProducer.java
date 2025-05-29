package kr.hhplus.be.server.infrastructure.kafka;

import kr.hhplus.be.server.domain.user.UserCouponPublishedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCouponPublishProducer {

    private final KafkaTemplate<String, UserCouponPublishedEvent> kafkaTemplate;

    public void sendPublishRequest(UserCouponPublishedEvent request) {
        kafkaTemplate.send("coupon-publish-request", String.valueOf(request.getCouponId()), request);
    }

}
