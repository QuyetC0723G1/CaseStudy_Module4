package com.group4.controller;

import com.group4.model.Category;
import com.group4.service.iplm.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> showAllCategory(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }
}
