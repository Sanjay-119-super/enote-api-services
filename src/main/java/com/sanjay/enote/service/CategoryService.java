package com.sanjay.enote.service;

import com.sanjay.enote.dto.CategoryDto;
import com.sanjay.enote.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * ➤ Purpose:
 *     Ye interface category ke business logic ko define karta hai.
 *     Iska implementation CategoryServiceImpl class karegi.
 *
 * ➤ Real-World Use:
 *     Controller se direct repository call na karke, service layer ke through logic handle hota hai.
 *     Jaise validation, conversion DTO <-> Entity, aur custom rules.
 *
 * ➤ Best Practice:
 *     Service layer banana ek standard software architecture pattern hai
 *     jo system ko modular aur testable banata hai.
 */

@Service
public interface CategoryService {

    /**
     *  Category save karta hai (create ya update)
     * Use case: Jab user form submit karta hai naya ya edit hua category
     *
     * @param category - category data DTO form me
     * @return true agar save successfully ho gaya
     */
    boolean saveCategory(CategoryDto category);

    /**
     *  Saari categories return karta hai (deleted bhi ho sakti hain)
     * Use case: Admin listing me full list chahiye
     *
     * @return List of CategoryDto
     */
    List<CategoryDto> getAllCategory();

    /**
     *  Sirf active aur non-deleted categories deta hai
     * Use case: Frontend UI me dikhane ke liye sirf valid categories
     *
     * @return List of CategoryResponse
     */
    List<CategoryResponse> getActiveCategory();

    /**
     *  Ek specific category ka detail deta hai ID ke basis pe
     * Use case: Jab kisi category ka detail dekhna ya edit karna ho
     *
     * @param id - category ID
     * @return CategoryDto with data
     */
    CategoryDto getCategoryDetailsById(Integer id) throws Exception;

    /**
     *  Category ko soft delete karta hai (mark as deleted)
     * Use case: Jab user delete button dabata hai
     *
     * @param id - category ID
     * @return true agar delete successfully ho gaya
     */
    boolean deleteCategory(Integer id);
}
