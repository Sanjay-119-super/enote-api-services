package com.sanjay.enote.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Category entity class
 *
 * ➤ Purpose:
 *     Is class ka use database table `categories` ko represent karne ke liye kiya jaata hai.
 *
 * ➤ Inheritance:
 *     Ye class `BaseModel` ko extend karti hai, jisme audit fields jaise:
 *     - isActive, isDeleted, createdBy, createdOn, updatedBy, updatedOn automatically included hote hain.
 *
 * ➤ Real Use:
 *     Backend me jab bhi koi category create, update ya retrieve karte ho,
 *     toh yahi entity database ke records ke saath interact karti hai.
 *
 * ➤ Notes:
 *     - `@Entity` → Spring isko DB entity ke form me treat karega.
 *     - `@Table(name = "categories")` → Ye explicitly table ka naam set karta hai.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseModel {

    /**
     * Category ka primary key field
     * Auto-increment hoga DB me
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * Category ka naam
     * Ye required field hai, max length 100 characters
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Category ka description
     * Optional text field, larger content ke liye TEXT column type
     */
    @Column(columnDefinition = "TEXT")
    private String description;

}
