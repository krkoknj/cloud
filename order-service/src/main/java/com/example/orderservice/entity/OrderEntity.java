package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.vo.ResponseOrder;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String userId;
    private String orderId;
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    @Builder
    public OrderEntity(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String userId, String orderId, Date createdAt) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

    protected OrderEntity() {

    }

    public OrderDto toDto() {
        return OrderDto.builder()
                .orderId(orderId)
                .unitPrice(unitPrice)
                .qty(qty)
                .totalPrice(totalPrice)
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
                .createdAt(createdAt)
                .build();
    }
}
