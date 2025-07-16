package com.sanjay.enote.service;

import com.sanjay.enote.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
     boolean saveCategory(Category category);
    List<Category> getAllCategory();
}
