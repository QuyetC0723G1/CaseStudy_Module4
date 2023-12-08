package com.group4.service.iplm;

import com.group4.model.Customer;
import com.group4.repository.CustomerRepository;
import com.group4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findOneById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
