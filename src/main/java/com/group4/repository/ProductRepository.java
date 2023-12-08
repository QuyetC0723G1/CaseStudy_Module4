package com.group4.repository;

import com.group4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findByNameContainsIgnoreCase(String name);
    Iterable<Product> findAllByCategoryIdIn(List<Long> categoriesId);
    Iterable<Product> findAllByIdIn(List<Long> ids);

    Page<Product> findAllByCategoryIdIn(Pageable pageable,List<Long> categoriesId);


//    Page<Product> findAll(Pageable pageable, Sort sort);
}
