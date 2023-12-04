package com.group4.service.iplm;

import com.group4.model.Product;
import com.group4.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class ProductService implements IProductService {
    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
