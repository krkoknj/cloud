package com.example.orderservice.vo;

import com.example.orderservice.dto.OrderDto;
import lombok.Builder;
import lombok.Data;

@Data
public class RequestOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;

    @Builder
    public RequestOrder(String productId, Integer qty, Integer unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDto toOrderDto() {
        return OrderDto.builder()
                .productId(productId)
                .qty(qty)
                .unitPrice(unitPrice)
                .build();
    }
}
