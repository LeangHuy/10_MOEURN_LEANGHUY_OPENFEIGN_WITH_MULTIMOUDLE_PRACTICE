package com.config.service;


import com.config.model.DTO.CustomerRequest;
import com.config.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

}
