package com.config.client;

import com.config.response.ApiResponse;
import com.config.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",url = "http://localhost:8081/api/v1/customers")
public interface CustomerFeignClient {
    @GetMapping("/{customerId}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long customerId);
}
