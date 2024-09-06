package com.config.model.DTO;


import com.config.model.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotNull @NotBlank String customerName,
        @NotNull @NotBlank @Email String customerEmail
) {
    public Customer toEntity() {
        return new Customer(null, this.customerName, this.customerEmail);
    }

    public Customer toEntity(Long customerId) {
        return new Customer(customerId, this.customerName, this.customerEmail);
    }
}
