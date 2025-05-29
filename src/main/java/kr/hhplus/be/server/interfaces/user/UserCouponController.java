package kr.hhplus.be.server.interfaces.user;

import kr.hhplus.be.server.application.user.UserCouponFacade;
import kr.hhplus.be.server.application.user.UserCouponResult;
import kr.hhplus.be.server.domain.user.UserCouponPublishedEvent;
import kr.hhplus.be.server.infrastructure.kafka.UserCouponPublishProducer;
import kr.hhplus.be.server.interfaces.user.UserCouponRequest.Publish;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserCouponController implements UserCouponApi {

    private final UserCouponFacade userCouponFacade;
    private final UserCouponPublishProducer userCouponPublishProducer;

    @Override
    @GetMapping("/{id}/coupons")
    public UserCouponResponse.Coupons getCoupons(@PathVariable Long id) {
        UserCouponResult.Coupons userCoupons = userCouponFacade.getUserCoupons(id);
        return UserCouponResponse.Coupons.of(userCoupons);
    }

    @Override
    @PostMapping("/{id}/coupons/publish")
    public ResponseEntity<Void> publishUserCoupon(@PathVariable("id") Long userId, @RequestBody Publish request) {
//        userCouponFacade.issueUserCoupon(request.toCriteria(userId));

        UserCouponPublishedEvent userCouponPublishedEvent = UserCouponPublishedEvent.of(request.getCouponId(), userId);
        userCouponPublishProducer.sendPublishRequest(userCouponPublishedEvent); // kafka 이벤트 발행

        return ResponseEntity.accepted().build(); // 비동기라 Accepted 처리
    }
}
