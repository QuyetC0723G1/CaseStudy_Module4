package com.group4.service.iplm;

import com.group4.model.Category;
import com.group4.model.Order;
import com.group4.repository.OrderRepository;
import com.group4.service.ICategoryService;
import com.group4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Iterable<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Order> findAllByCustomerId(Long id) {
        return orderRepository.findAllByCustomerId(id);
    }
}
