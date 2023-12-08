package com.group4.controller;

import com.group4.model.Product;
import com.group4.service.iplm.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> showAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
        List<Product> products = (List<Product>) productService.findByName(name);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/searchByCategory")
    public ResponseEntity<List<Product>> searchByCategory(@RequestBody List<Long> categoriesId) {
        if (categoriesId == null || categoriesId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Product> products = (List<Product>) productService.findALlByCategoryId(categoriesId);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<Product>> searchById(@PathVariable Long id) {
        Optional<Product> product = productService.findOneById(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @PostMapping("/searchByCateForUser")
    public ResponseEntity<Page<Product>> searchByCategory(@PageableDefault(value = 6) Pageable pageable, @RequestBody List<Long> categoriesId) {
        if (categoriesId == null || categoriesId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Product> products = productService.findAllByCategoryIdForUser(pageable, categoriesId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



//    for-user
    @GetMapping("/showAll")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam String sortOrder
    ) {
        Pageable pageable;
        if (sortOrder.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), "price");
            pageable = PageRequest.of(page, size, sort);
        }

        Page<Product> products = productService.findAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}


