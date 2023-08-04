package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer totalPrice;

    private Integer stock;
    private Date createdAt;

    @Builder
    public ResponseCatalog(String productId, String productName, Integer unitPrice, Integer totalPrice,Integer stock,Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }
}
