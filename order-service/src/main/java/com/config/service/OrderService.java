package com.config.service;

import com.config.enumeration.ECustomer;
import com.config.enumeration.EOrder;
import com.config.model.DTO.OrderRequest;
import com.config.response.OrderResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders(int pageNo, int pageSize, EOrder sortBy, Sort.Direction sortDirection);
    OrderResponse getOrderById(Long orderId);
    OrderResponse updateOrderById(Long orderId,OrderRequest orderRequest);
    void deleteOrderById(Long orderId);
}
