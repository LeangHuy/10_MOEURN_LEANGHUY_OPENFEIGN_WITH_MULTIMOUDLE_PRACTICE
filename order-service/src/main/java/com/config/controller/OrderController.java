package com.config.controller;

import com.config.enumeration.EOrder;
import com.config.model.DTO.OrderRequest;
import com.config.response.ApiResponse;
import com.config.response.OrderResponse;
import com.config.service.OrderService;
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
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create new order.")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Create new order successfully.")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .payload(orderService.createOrder(orderRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all orders.")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam EOrder sortBy,
            @RequestParam Sort.Direction sortDirection
    ) {
        ApiResponse<List<OrderResponse>> response = ApiResponse.<List<OrderResponse>>builder()
                .message("Get all orders successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(orderService.getAllOrders(pageNo, pageSize, sortBy, sortDirection))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by id.")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long orderId) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Get order by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(orderService.getOrderById(orderId))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Update order by id.")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderById(@Valid @RequestBody OrderRequest orderRequest, @PathVariable Long orderId) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Update order by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(orderService.updateOrderById(orderId,orderRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete customer by id.")
    public ResponseEntity<ApiResponse<OrderResponse>> deleteCustomerById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Delete order by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
