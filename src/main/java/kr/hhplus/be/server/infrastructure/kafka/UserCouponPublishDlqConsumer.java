package kr.hhplus.be.server.infrastructure.kafka;

import kr.hhplus.be.server.domain.user.UserCouponPublishedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCouponPublishDlqConsumer {

    @KafkaListener(topics = "user-coupon-publish-request-dlq", groupId = "user-coupon-publish-dlq-group")
    public void processFailedUserCoupon(ConsumerRecord<String, UserCouponPublishedEvent> record) {

        // 1. 장애/오류 메시지 저장, 알림 등
        // 2. 운영자에게 Slack, SMS 등으로 즉시 알림
        // 3. 수동 재처리 (필요시 retry)

        log.warn("DLQ 메시지 수신! userId={}, couponId={}, value={}",
            record.value().getUserId(), record.key(), record.value());

        // 재처리 로직을 작성하거나 운영툴로 수동 처리할 수 있다.
    }

}
