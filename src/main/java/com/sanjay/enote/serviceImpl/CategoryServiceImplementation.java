package com.sanjay.enote.serviceImpl;

import com.sanjay.enote.entity.Category;
import com.sanjay.enote.repository.CategoryRepository;
import com.sanjay.enote.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public boolean saveCategory(Category category) {
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        Category savedCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(savedCategory)){
            return false;
        }
        return true;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList;
    }
}
