package com.example.emailservicerabbitmq.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class MasterEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3014033880246198593L;

    @JsonIgnore
    @Column(name = "deleted", nullable = false)
    private Boolean delete = false;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 0;

    @Column(name = "restricted", nullable = false)
    private Boolean restricted = false;

    @Column(name = "created_date", columnDefinition = "DATETIME(3)")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_date", columnDefinition = "DATETIME(3)")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @PrePersist
    void onCreate() {
        this.setCreatedDate(new Date());
        this.setLastModifiedDate(new Date());
    }

    @PreUpdate
    void onUpdate() {
        this.setLastModifiedDate(new Date());
    }

}
