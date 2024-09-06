package com.config.service.serviceImpl;

import com.config.model.DTO.CustomerRequest;
import com.config.model.entity.Customer;
import com.config.repository.CustomerRepository;
import com.config.response.CustomerResponse;
import com.config.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save( customerRequest.toEntity()).toCustomerResponse();
    }
}
