package com.group4.service.iplm;

import com.group4.model.ProductImages;
import com.group4.service.IProductImagesService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductImagesService implements IProductImagesService {
    @Override
    public Iterable<ProductImages> findAll() {
        return null;
    }

    @Override
    public Optional<ProductImages> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductImages save(ProductImages productImages) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
