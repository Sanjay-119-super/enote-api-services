package com.sanjay.enote.repository;

import com.sanjay.enote.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CategoryRepository
 *
 * ➤ Purpose:
 *     Ye interface Category entity ke liye database operations handle karta hai.
 *     Ye Spring Data JPA ka built-in interface JpaRepository ko extend karta hai.
 *
 * ➤ Real-World Use:
 *     CRUD operations jaise save(), findById(), deleteById() automatically available ho jaate hain.
 *     Plus, custom queries bhi define ki gayi hain active + deleted category ke basis pe.
 *
 * ➤ Benefits:
 *     - Boilerplate code avoid hota hai
 *     - Spring automatically implementation create karta hai
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     *  Active aur Non-deleted Categories fetch karta hai
     * Use case: Sirf valid categories UI me dikhani ho
     */
    List<Category> findByIsActiveTrueAndIsDeletedFalse();

    /**
     *  Saari Non-deleted Categories fetch karta hai
     * Use case: Admin panel me dikhane ke liye, chahe active ho ya inactive
     */
    List<Category> findAllByIsDeletedFalse();

    /**
     *  Specific Category by ID fetch karta hai jo deleted na ho
     * Use case: Category detail screen ya edit form ke liye
     */
    Optional<Category> findByCategoryIdAndIsDeletedFalse(Integer id);
}
