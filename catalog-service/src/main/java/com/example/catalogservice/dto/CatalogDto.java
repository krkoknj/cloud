package com.example.catalogservice.dto;

import com.example.catalogservice.entity.CatalogEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    @Builder
    public CatalogDto(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String orderId, String userId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.userId = userId;
    }

    public CatalogEntity toEntity() {
        return CatalogEntity.builder()
                .unitPrice(unitPrice)
                .build();
    }
}
