package com.config.service;

import com.config.enumeration.ECustomer;
import com.config.enumeration.EProduct;
import com.config.model.DTO.ProductRequest;
import com.config.response.ProductResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts(int pageNo, int pageSize, EProduct sortBy, Sort.Direction sortDirection);
    ProductResponse getProduct(Long productId);
    ProductResponse updateProduct(Long productId, ProductRequest productRequest);
    void deleteProduct(Long productId);
}
