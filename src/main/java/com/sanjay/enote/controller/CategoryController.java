package com.sanjay.enote.controller;

import com.sanjay.enote.entity.Category;
import com.sanjay.enote.service.CategoryService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody Category category){
        boolean savedCategory = categoryService.saveCategory(category);
        if (savedCategory){
            return new ResponseEntity<>("Saved Category Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Note Saved Category",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-category")
    public ResponseEntity<?> getAllCategory(){
        List<Category> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }
}
