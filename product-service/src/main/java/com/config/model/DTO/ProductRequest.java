package com.config.model.DTO;

import com.config.model.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank @NotNull String productName,
        @Positive @NotNull Double price
) {
    public Product toEntity() {
        return new Product(null, productName,price);
    }

    public Product toEntity(Long productId) {
        return new Product(productId, productName,price);
    }
}
