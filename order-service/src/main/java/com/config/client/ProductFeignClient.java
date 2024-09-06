package com.config.client;

import com.config.response.ApiResponse;
import com.config.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8082/api/v1/products")
public interface ProductFeignClient {
    @GetMapping("/{productId}")
    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long productId);
}
