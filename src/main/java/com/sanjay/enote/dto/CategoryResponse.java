package com.sanjay.enote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CategoryResponse class ek response DTO hai
 * jo sirf limited aur important category info user ko return karta hai.
 *
 *
 * Ye class mainly GET API responses ke liye use hoti hai
 * jisme active ya filtered categories ke naam, description, aur ID dikhaya jata hai.
 *
 *
 *
 * Real-World Use Case:
 * Frontend ya client ko unnecessary fields jaise `createdBy`, `updatedOn` nahi bhejna hota,
 * isliye hum CategoryResponse use karte hain ek clean response ke liye.
 *
 *
 *
 * Isme humne Lombok annotations use kiye hain: @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor
 * taaki boilerplate code jaise getter/setter manually na likhna pade.
 *
 *
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    /**
     * Category ki unique ID.
     * Ye backend me identify karne ke kaam aata hai kis category ki baat ho rahi hai.
     */
    private Integer categoryId;

    /**
     * Category ka naam.
     * Jaise: "Personal Notes", "Work", "Urgent", etc.
     */
    private String name;

    /**
     * Category ka description ya short info.
     * Iska use hota hai category ko samajhne me, UI display me.
     */
    private String description;
}
