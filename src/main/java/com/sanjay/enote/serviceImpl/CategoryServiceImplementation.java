package com.sanjay.enote.serviceImpl;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
import com.sanjay.enote.entity.Category;
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

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        if (ObjectUtils.isEmpty(categoryDto.getCategoryId())) {
            // CREATE new Category
            Category category = modelMapper.map(categoryDto, Category.class);
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());

            Category saved = categoryRepo.save(category);
            return !ObjectUtils.isEmpty(saved);
        } else {
            //  UPDATE existing Category
            return updateCategory(categoryDto);
        }
    }

    //  Separate update logic: Update only specific fields safely
    private boolean updateCategory(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category existing = optionalCategory.get();

            //  Only updating modifiable fields
            existing.setName(categoryDto.getName());
            existing.setDescription(categoryDto.getDescription());
            existing.setIsActive(categoryDto.getIsActive());

            existing.setUpdatedBy(1);
            existing.setUpdatedOn(new Date());

            categoryRepo.save(existing);
            return true;
        } else {
            System.out.println("Update failed: Category ID not found: " + categoryDto.getCategoryId());
            return false;
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAllByIsDeletedFalse();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categoryList = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryDetailsById(Integer id) {
        Optional<Category> byId = categoryRepo.findById(id);
        return byId.map(category -> modelMapper.map(category, CategoryDto.class)).orElse(null);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepo.findByCategoryIdAndIsDeletedFalse(id);
        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }
}
