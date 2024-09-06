package com.config.model.DTO;

import com.config.model.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank @NotNull String productName,
        @NotBlank @NotNull Double price
) {
    public Product toEntity() {
        return new Product(null, productName,price);
    }
}
