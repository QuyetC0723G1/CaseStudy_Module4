package com.group4.controller;

import com.group4.model.ProductImages;
import com.group4.service.iplm.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_images")
@CrossOrigin("*")
public class ProductImagesController {
    @Autowired
    ProductImagesService productImagesService;

    @GetMapping("")
    public ResponseEntity<List<ProductImages>> showAllImage(){
        List<ProductImages> productImages = (List<ProductImages>) productImagesService.findAll();
        if (productImages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(productImages,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductImages> save(@RequestBody ProductImages productImages){
        return  new ResponseEntity<>(productImagesService.save(productImages),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductImages> delete(@PathVariable Long id){
        productImagesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductImages> update(@PathVariable Long id, @RequestBody ProductImages productImages){
        productImages.setId(id);
        productImagesService.save(productImages);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<List<ProductImages>> getAllByProductId(@PathVariable Long id){
        List<ProductImages> images = (List<ProductImages>) productImagesService.findAllByProductId(id);
        if (images.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(images,HttpStatus.OK);
    }
}
