package com.config.service.serviceImpl;

import com.config.enumeration.EProduct;
import com.config.exception.NotFoundException;
import com.config.model.DTO.ProductRequest;
import com.config.model.entity.Product;
import com.config.repository.ProductRepository;
import com.config.response.ProductResponse;
import com.config.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity()).toProductResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts(int pageNo, int pageSize, EProduct sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.name().toLowerCase());
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return productRepository.findAll(pageable).getContent().stream().map(Product::toProductResponse).toList();
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(()->new NotFoundException("Product not found.")).toProductResponse();
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity(productId)).toProductResponse();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->new NotFoundException("Product not found."));
        productRepository.delete(product);
    }
}
