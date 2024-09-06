package com.config.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private String customerEmail;
}
