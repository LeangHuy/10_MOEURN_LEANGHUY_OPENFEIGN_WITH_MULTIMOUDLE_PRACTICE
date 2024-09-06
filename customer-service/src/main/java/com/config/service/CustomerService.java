package com.config.service;


import com.config.enumeration.ECustomer;
import com.config.model.DTO.CustomerRequest;
import com.config.response.CustomerResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers(int pageNo, int pageSize, ECustomer sortBy, Sort.Direction sortDirection);
    CustomerResponse getCustomerById(Long customerId);
    CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest);
    void deleteCustomer(Long customerId);

}
