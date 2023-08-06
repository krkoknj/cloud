package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.vo.ResponseOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    @Builder
    public OrderDto(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String orderId, String userId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.userId = userId;
    }

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .qty(qty)
                .orderId(orderId)
                .userId(userId)
                .productId(productId)
                .build();
    }

    public ResponseOrder toResponseOrder() {
        return ResponseOrder.builder()
                .productId(productId)
                .qty(qty)
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .orderId(orderId)
                .build();
    }
}
