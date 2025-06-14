package kr.hhplus.be.server.infrastructure.order;

import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderExternalClient;
import kr.hhplus.be.server.domain.order.OrderInfoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderExternalClientImpl implements OrderExternalClient {

    @Override
    public void sendOrderMessage(OrderInfoEvent order) {
        log.info("외부 데이터 플랫폼 주문정보 저장 : {}", order);
    }
}