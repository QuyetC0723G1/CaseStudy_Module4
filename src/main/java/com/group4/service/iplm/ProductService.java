package com.group4.service.iplm;

import com.group4.model.Product;
import com.group4.repository.ProductRepository;
import com.group4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Iterable<Product> findALlByCategoryId(List<Long> categoriesId) {
        return productRepository.findAllByCategoryIdIn(categoriesId);
    }

    @Override
    public Iterable<Product> findAllByIdIn(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }
}
