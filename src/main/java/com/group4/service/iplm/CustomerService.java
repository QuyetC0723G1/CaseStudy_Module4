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
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findOneById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customer1.setDeleteFlag(true);
            customerRepository.save(customer1);
        }
    }

    @Override
    public boolean phoneIsUse(String phone) {
        boolean isUse = false;
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer cus : customers) {
            if (cus.getPhoneNumber().equals(phone)) {
                isUse  = true;
                break;
            }
        }
        return isUse;
    }

    @Override
    public Optional<Customer> findCustomerByUserId(Long userId) {
        return customerRepository.findCustomerByUserId(userId);
    }

    @Override
    public boolean emailIsUse(String email) {
        boolean isUse = false;
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer cus : customers) {
            if (cus.getEmail().equals(email)) {
                isUse  = true;
                break;
            }
        }
        return isUse;
    }
}
