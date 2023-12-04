package com.group4.service.iplm;

import com.group4.model.ProductImages;
import com.group4.repository.ProductImagesRepository;
import com.group4.service.IProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductImagesService implements IProductImagesService {
    @Autowired
    ProductImagesRepository productImagesRepository;
    @Override
    public Iterable<ProductImages> findAll() {
        return productImagesRepository.findAll();
    }

    @Override
    public Optional<ProductImages> findOneById(Long id) {
        return productImagesRepository.findById(id);
    }

    @Override
    public ProductImages save(ProductImages productImages) {
        return productImagesRepository.save(productImages);
    }

    @Override
    public void delete(Long id) {
        productImagesRepository.deleteById(id);
    }

    @Override
    public Iterable<ProductImages> findAllByProductId(Long productId) {
        return productImagesRepository.findAllByProductId(productId);
    }
}
