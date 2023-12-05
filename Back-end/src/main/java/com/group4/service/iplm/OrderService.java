package com.group4.service.iplm;

import com.group4.model.Category;
import com.group4.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements ICategoryService {
    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
