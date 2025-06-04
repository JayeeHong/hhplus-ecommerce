package kr.hhplus.be.server.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ProductOrderedEvent {

    private final List<ProductInfo.OrderItem> items;
    private final LocalDate orderDate;

    public ProductOrderedEvent(
        @JsonProperty("items") List<ProductInfo.OrderItem> items,
        @JsonProperty("orderDate") LocalDate orderDate) {

        this.items = items;
        this.orderDate = orderDate;
    }
}
