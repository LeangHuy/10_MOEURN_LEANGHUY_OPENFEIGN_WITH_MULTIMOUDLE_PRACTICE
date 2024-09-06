package com.config.controller;

import com.config.enumeration.ECustomer;
import com.config.enumeration.EProduct;
import com.config.model.DTO.ProductRequest;
import com.config.response.ApiResponse;
import com.config.response.ProductResponse;
import com.config.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product.")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Create a new product.")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .payload(productService.createProduct(productRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all products.")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam EProduct sortBy,
            @RequestParam Sort.Direction sortDirection
    ) {
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .message("Get all products successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(productService.getAllProducts(pageNo, pageSize, sortBy, sortDirection))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by id.")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable Long productId) {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Get product successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(productService.getProduct(productId))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update product by id.")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductRequest productRequest){
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Update product successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(productService.updateProduct(productId,productRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete product by id.")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("Delete product successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
