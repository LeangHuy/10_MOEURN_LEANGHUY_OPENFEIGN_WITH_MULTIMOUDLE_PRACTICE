package com.config.service.serviceImpl;

import com.config.enumeration.ECustomer;
import com.config.exception.NotFoundException;
import com.config.model.DTO.CustomerRequest;
import com.config.model.entity.Customer;
import com.config.repository.CustomerRepository;
import com.config.response.CustomerResponse;
import com.config.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toCustomerResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomers(int pageNo, int pageSize, ECustomer sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.name().toLowerCase());
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return customerRepository.findAll(pageable).getContent().stream().map(Customer::toCustomerResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()->new NotFoundException("Customer not found")).toCustomerResponse();
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
        getCustomerById(customerId);
        return customerRepository.save(customerRequest.toEntity(customerId)).toCustomerResponse();
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new NotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
