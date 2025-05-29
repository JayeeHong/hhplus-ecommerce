package kr.hhplus.be.server.domain.user;

import lombok.Getter;

@Getter
public class UserCouponPublishedEvent {

    private final Long couponId;
    private final Long userId;

    private UserCouponPublishedEvent(Long couponId, Long userId) {
        this.couponId = couponId;
        this.userId = userId;
    }

    public static UserCouponPublishedEvent of(Long couponId, Long userId) {
        return new UserCouponPublishedEvent(couponId, userId);
    }
}
