package com.sanjay.enote.repository;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByIsActiveTrueAndIsDeletedFalse();


    List<Category> findAllByIsDeletedFalse();


    Optional<Category> findByCategoryIdAndIsDeletedFalse(Integer id);
}
