package com.sanjay.enote.controller;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
import com.sanjay.enote.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController ka kaam hai category-related REST APIs ko handle karna.
 *
 * Is controller me CRUD endpoints defined hain jinke through hum:
 * - Category create/update kar sakte hain
 * - All categories fetch kar sakte hain
 * - Active categories dekh sakte hain
 * - Particular category details by ID dekh sakte hain
 * - Category delete kar sakte hain
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Ye API category ko save ya update karti hai.
     * Agar categoryId null hai to new category save hoti hai,
     * aur agar present hai to existing category update hoti hai.
     *
     * @param category CategoryDto object jo client se aata hai (JSON ke form me)
     * @return ResponseEntity<String> status message with HTTP status code
     */
    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto category){
        boolean savedCategory = categoryService.saveCategory(category);
        if (savedCategory){
            return new ResponseEntity<>("Saved Category Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Note Saved Category", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Ye API saari categories ko return karti hai jo delete nahi hui hain.
     *
     * @return ResponseEntity<List<CategoryDto>> agar categories milti hain to 200 OK,
     *         warna 204 No Content
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllCategory(){
       /* String nm = null;
        nm.toLowerCase();*/
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    /**
     * Ye API active aur non-deleted categories ko return karti hai.
     *
     * @return ResponseEntity<List<CategoryResponse>> agar active categories milti hain to 200 OK,
     *         warna 204 No Content
     */
    @GetMapping("/get-active-category")
    public ResponseEntity<?> getActiveCategory(){
        List<CategoryResponse> allCategory = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    /**
     * Ye API specific category details return karti hai based on ID.
     *
     * @param id Integer value jo path se aata hai (URL parameter)
     * @return ResponseEntity<CategoryDto> agar category milti hai to 200 OK,
     *         warna 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception{

        CategoryDto categoryDtoById = categoryService.getCategoryDetailsById(id);
        if (ObjectUtils.isEmpty(categoryDtoById)){
            return new ResponseEntity<>("Category not found with Id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDtoById, HttpStatus.OK);
    }

    /**
     * Ye API kisi category ko soft delete karti hai by setting isDeleted = true.
     *
     * @param id Integer value jo delete karne wali category ka ID hai
     * @return ResponseEntity<String> deletion status ke saath
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        boolean deleteCategory = categoryService.deleteCategory(id);
        if (deleteCategory){
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not deleted successfully", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
