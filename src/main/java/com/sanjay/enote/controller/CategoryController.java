package com.sanjay.enote.controller;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
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
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto category){
        boolean savedCategory = categoryService.saveCategory(category);
        if (savedCategory){
            return new ResponseEntity<>("Saved Category Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Note Saved Category",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }

    @GetMapping("/get-active-category")
    public ResponseEntity<?> getActiveCategory(){
        List<CategoryResponse> allCategory = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id){
        CategoryDto categoryDtoById=categoryService.getCategoryDetailsById(id);

        if (ObjectUtils.isEmpty(categoryDtoById)){
            return new ResponseEntity<>("Category not fount with Id: "+ id,HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(categoryDtoById,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        boolean deleteCategory=categoryService.deleteCategory(id);
        if (deleteCategory){
            return new ResponseEntity<>("Category deleted successflly",HttpStatus.OK);
        }

        return new ResponseEntity<>("Category not deleted successflly",HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
