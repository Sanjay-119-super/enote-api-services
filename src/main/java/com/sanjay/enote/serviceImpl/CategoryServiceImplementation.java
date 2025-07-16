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

import java.util.Collections;
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

 /*       Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setIsActive(categoryDto.getIsActive());
*/
        Category category = modelMapper.map(categoryDto, Category.class);

        category.setIsDeleted(false);
        category.setCreatedBy(1);
        Category savedCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(savedCategory)){
            return false;
        }
        return true;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAllByIsDeletedFalse();

        List<CategoryDto> caegoryDtoList = categoryList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        return caegoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categoryList = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> collectList = categoryList.stream().map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());

        return collectList;
    }

    @Override
    public CategoryDto getCategoryDetailsById(Integer id) {
        Optional<Category> byId = categoryRepo.findById(id);

        if (byId.isPresent()){
            Category category = byId.get();
            return modelMapper.map(category,CategoryDto.class);
        }
        return null;
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
