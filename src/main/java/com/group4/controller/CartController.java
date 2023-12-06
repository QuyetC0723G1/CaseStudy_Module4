package com.group4.controller;

import com.group4.model.Product;
import com.group4.service.iplm.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ProductService productService;
    @PostMapping("")
    public ResponseEntity<Iterable<Product>> getAll(@RequestBody List<Long> ids){
        return new ResponseEntity<>(productService.findAllByIdIn(ids), HttpStatus.OK);
    }
}
