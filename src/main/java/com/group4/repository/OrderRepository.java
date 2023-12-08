package com.group4.repository;

import com.group4.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Iterable<Order> findAllByCustomerId(Long id);
}
