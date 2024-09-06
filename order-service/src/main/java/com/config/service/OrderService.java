package com.config.service;

import com.config.model.DTO.OrderRequest;
import com.config.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
