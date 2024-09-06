package com.config.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private CustomerResponse customer;
    private List<ProductResponse> products;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
}
