package com.sanjay.enote.serviceImpl;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
import com.sanjay.enote.entity.Category;
import com.sanjay.enote.exception.ResourceNotFoundException;
import com.sanjay.enote.repository.CategoryRepository;
import com.sanjay.enote.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  CategoryServiceImplementation
 *
 * 🔹 Purpose:
 *     Ye class business logic handle karti hai Category related operations ke liye —
 *     jaise create, update, get all, get active, get by id, and soft delete.
 *
 * 🔹 Real-World Example:
 *     Ek Note-taking App jisme alag-alag Category banani ho (Work, Personal, Study),
 *     unko CRUD (Create, Read, Update, Delete) operations se manage karna ho.
 */

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepo;

    /**
     * 🔹 Method: saveCategory()
     * 🔹 Purpose:
     *     Ye method naya category create karta hai agar ID null ho,
     *     warna existing category update karta hai.
     *
     * 🔹 Parameters:
     *     - CategoryDto categoryDto: Client se aayi category data
     *
     * 🔹 Returns:
     *     - true if successful, false otherwise
     */
    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        if (ObjectUtils.isEmpty(categoryDto.getCategoryId())) {
            //  Nayi category ban rahi hai
            Category category = modelMapper.map(categoryDto, Category.class);
            category.setIsDeleted(false);        // Soft delete flag default false
            category.setCreatedBy(1);            // Hardcoded createdBy
            category.setCreatedOn(new Date());   // Current timestamp

            Category saved = categoryRepo.save(category);
            return !ObjectUtils.isEmpty(saved);  // Agar save hua to true
        } else {
            //  Purani category ko update karna hai
            return updateCategory(categoryDto);
        }
    }

    /**
     * 🔹 Private Method: updateCategory()
     * 🔹 Purpose:
     *     Sirf existing category update karta hai safe way mein (only specific fields).
     *
     * 🔹 Parameters:
     *     - CategoryDto: Updated data
     *
     * 🔹 Returns:
     *     - true if update success, false if ID not found
     */
    private boolean updateCategory(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category existing = optionalCategory.get();

            //  Sirf important fields ko update kar rahe hain
            existing.setName(categoryDto.getName());
            existing.setDescription(categoryDto.getDescription());
            existing.setIsActive(categoryDto.getIsActive());

            existing.setUpdatedBy(1);            // Hardcoded for now
            existing.setUpdatedOn(new Date());   // Timestamp

            categoryRepo.save(existing);
            return true;
        } else {
            System.out.println("Update failed: Category ID not found: " + categoryDto.getCategoryId());
            return false;
        }
    }

    /**
     * 🔹 Method: getAllCategory()
     * 🔹 Purpose:
     *     Sabhi non-deleted categories list return karta hai.
     *
     * 🔹 Returns:
     *     - List<CategoryDto> : All categories (isDeleted = false)
     */
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAllByIsDeletedFalse();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 🔹 Method: getActiveCategory()
     * 🔹 Purpose:
     *     Sirf active + non-deleted categories return karta hai.
     *
     * 🔹 Returns:
     *     - List<CategoryResponse>
     */
    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categoryList = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * 🔹 Method: getCategoryDetailsById()
     * 🔹 Purpose:
     *     Specific category ki detail return karta hai by ID.
     *
     * 🔹 Parameters:
     *     - id : categoryId
     *
     * 🔹 Returns:
     *     - CategoryDto ya null (agar nahi mili to)
     */
    @Override
    public CategoryDto getCategoryDetailsById(Integer id) throws Exception{
        Category category1 = categoryRepo.findByCategoryIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id" + id));

        if (!ObjectUtils.isEmpty(category1)){
            
            return modelMapper.map(category1,CategoryDto.class);
        }
        return null;
    }

    /**
     * 🔹 Method: deleteCategory()
     * 🔹 Purpose:
     *     Category ko "soft delete" karta hai (isDeleted = true)
     *
     * 🔹 Parameters:
     *     - id : categoryId
     *
     * 🔹 Returns:
     *     - true agar delete hua, false agar nahi mila
     */
    @Override
    public boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepo.findByCategoryIdAndIsDeletedFalse(id);
        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            category.setIsDeleted(true);     // Soft delete mark
            categoryRepo.save(category);
            return true;
        }
        return false;
    }
}
