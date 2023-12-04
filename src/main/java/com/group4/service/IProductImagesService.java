package com.group4.service;

import com.group4.model.ProductImages;

import java.util.List;

public interface IProductImagesService extends IGenerateService<ProductImages> {
    Iterable<ProductImages> findAllByProductId(Long productId);
}
