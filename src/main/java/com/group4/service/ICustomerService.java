package com.group4.service;

import com.group4.model.Customer;

import java.util.Optional;

public interface ICustomerService extends IGenerateService<Customer> {
        boolean phoneIsUse(String phone);
        boolean emailIsUse(String email);
        Optional<Customer> findCustomerByUserId(Long userId);
}
