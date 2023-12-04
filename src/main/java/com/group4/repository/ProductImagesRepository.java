package com.group4.repository;

import com.group4.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages,Long> {
    Iterable<ProductImages> findAllByProductId(Long productId);
}
