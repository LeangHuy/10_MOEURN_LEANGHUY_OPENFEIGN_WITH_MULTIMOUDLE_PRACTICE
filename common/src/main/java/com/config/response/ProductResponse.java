package com.config.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private Double productPrice;
}
