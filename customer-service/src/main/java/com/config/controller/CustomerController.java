package com.config.controller;

import com.config.enumeration.ECustomer;
import com.config.model.DTO.CustomerRequest;
import com.config.response.ApiResponse;
import com.config.response.CustomerResponse;
import com.config.service.CustomerService;
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
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer.")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Create a new customer successfully.")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .payload(customerService.createCustomer(customerRequest))
                .timestamp(LocalDateTime.now())
                .build();
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all customers.")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam ECustomer sortBy,
            @RequestParam Sort.Direction sortDirection
    ) {
        ApiResponse<List<CustomerResponse>> response = ApiResponse.<List<CustomerResponse>>builder()
                .message("Get all customers successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(customerService.getAllCustomers(pageNo, pageSize, sortBy, sortDirection))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get customer by id.")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long customerId) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Get customer by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(customerService.getCustomerById(customerId))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    @Operation(summary = "Update customer by id.")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById(@Valid @RequestBody CustomerRequest customerRequest, @PathVariable Long customerId) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Update customer by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .payload(customerService.updateCustomer(customerId,customerRequest))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    @Operation(summary = "Delete customer by id.")
    public ResponseEntity<ApiResponse<CustomerResponse>> deleteCustomerById(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Delete customer by id successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
