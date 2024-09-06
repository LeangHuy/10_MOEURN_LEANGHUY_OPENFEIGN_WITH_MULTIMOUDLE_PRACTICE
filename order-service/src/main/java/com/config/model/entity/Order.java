package com.config.model.entity;

import com.config.response.CustomerResponse;
import com.config.response.OrderResponse;
import com.config.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ElementCollection
    private List<Long> productIds;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;

    public OrderResponse toResponse(CustomerResponse customerResponse, List<ProductResponse> productResponses) {
        return new OrderResponse(this.id, customerResponse, productResponses, this.orderDate);
    }
}
