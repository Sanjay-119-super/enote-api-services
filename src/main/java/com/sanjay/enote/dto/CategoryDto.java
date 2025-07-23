package com.sanjay.enote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

/**
 * CategoryDto ek Data Transfer Object (DTO) hai jo Category entity ki data values ko
 * layer to layer (jaise Controller se Service, ya UI se Backend) safely transfer karne ke liye use hota hai.
 *
 * Iska use backend me kisi bhi Category object ka simplified version banane ke liye hota hai
 * taaki hum database entities ko directly expose na karein.
 *
 * Real-World Use:
 * - Category list dikhana frontend pe
 * - Category ko save/update/delete karna
 * - APIs ke through Category ka data exchange karna
 *
 * Fields:
 * - Har field represent karta hai ek attribute of category, jaisa ki name, description, status, etc.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    /**
     * Category ka unique identifier.
     * Ye mostly database primary key hoti hai.
     *
     * Example: 101
     */
    private Integer categoryId;

    /**
     * Category ka naam.
     *
     * Example: "Study Notes"
     */
    private String name;

    /**
     * Category ka short description.
     *
     * Example: "All notes related to study subjects."
     */
    private String description;

    /**
     * Category active hai ya nahi.
     * true = active, false = inactive.
     *
     * Use: List me sirf active categories dikhane ke liye.
     */
    private Boolean isActive;

    /**
     * User ID jisne category create ki thi.
     * Isse pata chalta hai kis user ne ye data banaya.
     */
    private Integer createdBy;

    /**
     * Jab category create hui thi us date ka record.
     * Use hota hai audit purpose ke liye.
     */
    private Date createdOn;

    /**
     * User ID jisne last time category ko update kiya.
     */
    private Integer updatedBy;

    /**
     * Jab category last time update hui us date ka record.
     */
    private Date updatedOn;
}
