package com.mettusoft.crud.repository.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String createBy;
    private String modifyBy;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @PrePersist
    public void addAuditInfo() {
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.createBy = "MettuS";
        this.modifyBy = "MettuS";
    }

    @PreUpdate
    public void updateAuditInfo() {
        this.modifyDate = LocalDateTime.now();
        this.modifyBy = "MettuS1";
    }

}