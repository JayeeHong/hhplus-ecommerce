package kr.hhplus.be.server.interfaces.user;

import kr.hhplus.be.server.application.user.UserCouponFacade;
import kr.hhplus.be.server.application.user.UserCouponResult;
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

    @Override
    @GetMapping("/{id}/coupons")
    public UserCouponResponse.Coupons getCoupons(@PathVariable Long id) {
        UserCouponResult.Coupons userCoupons = userCouponFacade.getUserCoupons(id);
        return UserCouponResponse.Coupons.of(userCoupons);
    }

    @Override
    @PostMapping("/{id}/coupons/publish")
    public void chargeBalance(Long userId, Publish request) {
        userCouponFacade.issueUserCoupon(request.toCriteria(userId));
    public ResponseEntity<Void> publishUserCoupon(@PathVariable("id") Long userId, @RequestBody Publish request) {
        return ResponseEntity.accepted().build(); // 비동기라 Accepted 처리
    }
}
