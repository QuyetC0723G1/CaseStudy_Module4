package com.group4.service;

import com.group4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    Iterable<Product> findByName(String name);
    Iterable<Product> findALlByCategoryId(List<Long> categoriesId);
    Iterable<Product> findAllByIdIn(List<Long> ids);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategoryIdForUser(Pageable pageable,List<Long> categoriesId);
   Page<Product> getAllProductsSortedByPriceUp(int page, int size);
}
