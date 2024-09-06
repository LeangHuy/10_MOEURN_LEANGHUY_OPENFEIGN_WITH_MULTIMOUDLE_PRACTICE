package com.config.model.DTO;

import com.config.model.entity.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrderRequest(
        @NotBlank @NotNull Long customerId,
        @NotBlank @NotNull List<Long> productIds
) {
    public Order toEntity() {
        LocalDate orderDate = LocalDate.now();
        return new Order(null, customerId, productIds, orderDate);
    }
}
