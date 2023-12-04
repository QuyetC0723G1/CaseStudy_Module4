package com.group4.service.iplm;

import com.group4.model.OrderDetail;
import com.group4.service.IOrderDetailService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailService implements IOrderDetailService {
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
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
