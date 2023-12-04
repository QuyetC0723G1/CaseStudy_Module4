package com.group4.service;

import com.group4.model.Product;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    Iterable<Product> findByName(String name);
    Iterable<Product> findALlByCategoryId(List<Long> categoriesId);
}
