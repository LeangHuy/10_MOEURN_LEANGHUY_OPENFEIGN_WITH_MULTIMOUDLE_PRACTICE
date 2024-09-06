package com.config.service.serviceImpl;

import com.config.client.CustomerFeignClient;
import com.config.client.ProductFeignClient;
import com.config.enumeration.EOrder;
import com.config.exception.NotFoundException;
import com.config.model.DTO.OrderRequest;
import com.config.model.entity.Order;
import com.config.repository.OrderRepository;
import com.config.response.ApiResponse;
import com.config.response.CustomerResponse;
import com.config.response.OrderResponse;
import com.config.response.ProductResponse;
import com.config.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;
    private final CustomerFeignClient customerFeignClient;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        //find product
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Long productId : orderRequest.productIds()){
            ProductClient(productResponseList, productId);
        }
        //find customer
        CustomerResponse customerResponse = null;
        ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById = customerFeignClient.getCustomerById(orderRequest.customerId());
        if (getCustomerById.getStatusCode().is2xxSuccessful()){
            ApiResponse<CustomerResponse> apiResponse = getCustomerById.getBody();
            assert apiResponse != null;
            customerResponse = apiResponse.getPayload();
        }
        return  orderRepository.save(orderRequest.toEntity()).toResponse(customerResponse,productResponseList);
    }

    @Override
    public List<OrderResponse> getAllOrders(int pageNo, int pageSize, EOrder sortBy, Sort.Direction sortDirection) {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        OrderResponse getOrderById;
        Sort sort = Sort.by(sortDirection, sortBy.name().toLowerCase());
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        List<Order> getAllOrder = orderRepository.findAll(pageable).getContent();
        for (Order order : getAllOrder){
            getOrderById = getOrderById(order.getId());
            orderResponseList.add(getOrderById);
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found."));
        //find product
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Long productId : order.getProductIds()){
            ProductClient(productResponseList, productId);
        }

        //find customer
        CustomerResponse customerResponse = null;
        ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById = customerFeignClient.getCustomerById(order.getCustomerId());
        if (getCustomerById.getStatusCode().is2xxSuccessful()){
            ApiResponse<CustomerResponse> apiResponse = getCustomerById.getBody();
            assert apiResponse != null;
            customerResponse = apiResponse.getPayload();
        }
        return order.toResponse(customerResponse,productResponseList);
    }

    @Override
    public OrderResponse updateOrderById(Long orderId, OrderRequest orderRequest) {
        getOrderById(orderId);
        orderRepository.save(orderRequest.toEntity(orderId));
        return getOrderById(orderId);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found."));
        orderRepository.delete(order);
    }

    private void ProductClient(List<ProductResponse> productResponseList, Long productId) {
        ResponseEntity<ApiResponse<ProductResponse>> getProductById = productFeignClient.getProductById(productId);
        if (getProductById.getStatusCode().is2xxSuccessful()){
            ApiResponse<ProductResponse> apiResponse = getProductById.getBody();
            assert apiResponse != null;
            ProductResponse productResponse = apiResponse.getPayload();
            productResponseList.add(productResponse);
        }
    }
}
