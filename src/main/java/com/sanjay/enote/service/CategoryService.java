package com.sanjay.enote.service;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
import com.sanjay.enote.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
     boolean saveCategory(CategoryDto category);
    List<CategoryDto> getAllCategory();

    List<CategoryResponse> getActiveCategory();
}
