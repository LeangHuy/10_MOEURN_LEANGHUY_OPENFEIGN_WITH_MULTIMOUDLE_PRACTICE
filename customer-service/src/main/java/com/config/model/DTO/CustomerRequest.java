package com.config.model.DTO;


import com.config.model.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotNull @NotBlank String customerName,
        @NotNull @NotBlank String customerEmail
) {
    public Customer toEntity() {
        return new Customer(null, this.customerName, this.customerEmail);
    }
}
