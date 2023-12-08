package com.group4.service.iplm;

import com.group4.model.OrderDetail;
import com.group4.repository.OrderDetailRepository;
import com.group4.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public Iterable<OrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(Long id) {

    }
}
