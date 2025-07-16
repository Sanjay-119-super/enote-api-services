package com.sanjay.enote.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_by")
    private Integer createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updatedOn;

    // Automatically set time on insert/update
    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }

}
