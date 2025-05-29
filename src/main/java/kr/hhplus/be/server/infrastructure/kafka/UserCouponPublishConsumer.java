package kr.hhplus.be.server.infrastructure.kafka;

import kr.hhplus.be.server.domain.user.UserCouponCommand.Publish;
import kr.hhplus.be.server.domain.user.UserCouponPublishedEvent;
import kr.hhplus.be.server.domain.user.UserCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCouponPublishConsumer {

    private final UserCouponService userCouponService;
    private final KafkaTemplate<String, UserCouponPublishedEvent> kafkaTemplate;

    @KafkaListener(topics = "user-coupon-publish-request", groupId = "user-coupon-publish-group")
    @Transactional
    public void consume(ConsumerRecord<String, UserCouponPublishedEvent> record) {
        UserCouponPublishedEvent request = record.value();

        try {
            Publish userCouponCommand = Publish.toUserCouponCommandPublish(request.getUserId(), request.getCouponId());

            userCouponService.createUserCouponRedis(userCouponCommand);
            userCouponService.createUserCoupon(userCouponCommand);
        } catch (Exception e) {
            // kafka 메시지 전송 실패 시 DLQ 토픽으로 메시지 전송
            UserCouponPublishedEvent original = record.value();
            kafkaTemplate.send("user-coupon-publish-request-dlq", record.key(), original);

            // 로그 기록
            log.error("쿠폰 발급 처리 실패, DLQ로 이동. userId={}, couponId={}, error={}",
                original.getUserId(), original.getCouponId(), e.getMessage(), e);
        }
    }
}
