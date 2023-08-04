package com.example.catalogservice.entity;

import com.example.catalogservice.vo.ResponseCatalog;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "catalog")
public class CatalogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;

    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    protected CatalogEntity() {

    }

    @Builder
    public CatalogEntity(String productId, String productName, Integer stock, Integer unitPrice, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
    }

    public ResponseCatalog toResponseCatalog() {
        return ResponseCatalog.builder()
                .createdAt(createdAt)
                .totalPrice(unitPrice)
                .productName(productName)
                .productId(productId)
                .stock(stock)
                .build();
    }

}
