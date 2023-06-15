package com.example.automarket.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity implements Serializable {

    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @JsonIgnore
    @Column(name = "updated_by", updatable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityAudit other)) return false;
        if (!super.equals(o)) return false;
        return createdBy.equals(other.createdBy) &&
                updatedBy.equals(other.updatedBy) &&
                createdAt.equals(other.createdAt) &&
                updatedAt.equals(other.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                createdBy, updatedBy, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BaseEntityAudit{" +
                "createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "} " +
                super.toString();
    }
}