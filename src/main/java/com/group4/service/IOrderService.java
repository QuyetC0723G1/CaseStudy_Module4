package com.group4.service;

import com.group4.model.Order;

public interface IOrderService extends IGenerateService<Order> {
    Iterable<Order> findAllByCustomerId(Long id);
}
