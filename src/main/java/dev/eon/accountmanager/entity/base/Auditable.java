package dev.eon.accountmanager.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eon.accountmanager.entity.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    // #PROPERTIES
    @Column(name = "created_at")
    @CreatedDate
    @JsonIgnore
    protected Timestamp createdAt;

    @OneToOne()
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @CreatedBy
    @JsonIgnore
    protected User createdBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    @JsonIgnore
    protected Timestamp updatedAt;

    @OneToOne()
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @LastModifiedBy
    @JsonIgnore
    protected User updatedBy;

    // #SETTERS & GETTERS
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

}