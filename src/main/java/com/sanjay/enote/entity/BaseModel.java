package com.sanjay.enote.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * BaseModel ek common parent class hai jo sabhi entities ke liye
 * audit-related fields provide karta hai.
 *
 * Purpose:
 * - Har entity ke andar 'isActive', 'isDeleted', 'createdBy', 'createdOn',
 *   'updatedBy', 'updatedOn' jaise common fields reuse karne ke liye.
 *
 * Real-World Usage:
 * - Jab aapko kisi bhi table ke record ke creation ya updation ka tracking chahiye ho,
 *   jaise kisne banaya, kab banaya, kisne update kiya, kab update kiya.
 *
 * Benefits:
 * - Code duplication avoid hota hai.
 * - Saare audit fields centralized location se control ho jaate hain.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    /**
     * Record active hai ya nahi - usko represent karta hai.
     * Default: true
     */
    @Column(name = "is_active")
    private Boolean isActive = true;

    /**
     * Record deleted hai ya nahi - soft delete ke liye use hota hai.
     * Default: false
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    /**
     * Record kis user ne banaya - uska userId store karta hai.
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * Record kab banaya gaya - timestamp store karta hai.
     * Sirf insert ke time set hota hai.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    /**
     * Record kis user ne update kiya - uska userId store karta hai.
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * Record last time kab update hua - timestamp store karta hai.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updatedOn;

    /**
     * Ye method automatically call hoti hai jab record create hota hai.
     * Isme createdOn field ko current time se set karte hain.
     */
    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
    }

    /**
     * Ye method automatically call hoti hai jab record update hota hai.
     * Isme updatedOn field ko current time se set karte hain.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }
}
