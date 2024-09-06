package com.config.service.serviceImpl;

import com.config.client.CustomerFeignClient;
import com.config.client.ProductFeignClient;
import com.config.model.DTO.OrderRequest;
import com.config.repository.OrderRepository;
import com.config.response.ApiResponse;
import com.config.response.CustomerResponse;
import com.config.response.OrderResponse;
import com.config.response.ProductResponse;
import com.config.service.OrderService;
import lombok.AllArgsConstructor;
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
            ResponseEntity<ApiResponse<ProductResponse>> getProductById = productFeignClient.getProductById(productId);
            if (getProductById.getStatusCode().is2xxSuccessful()){
                ApiResponse<ProductResponse> apiResponse = getProductById.getBody();
                assert apiResponse != null;
                ProductResponse productResponse = apiResponse.getPayload();
                productResponseList.add(productResponse);
            }
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
}
