package com.config.model.DTO;

import com.config.model.entity.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record OrderRequest(
        @NotNull @Positive Long customerId,
        @NotNull @NotEmpty List<Long> productIds
) {
    public Order toEntity() {
        LocalDate orderDate = LocalDate.now();
        return new Order(null, customerId, productIds, orderDate);
    }
    public Order toEntity(Long orderId) {
        LocalDate orderDate = LocalDate.now();
        return new Order(orderId, customerId, productIds, orderDate);
    }
}
