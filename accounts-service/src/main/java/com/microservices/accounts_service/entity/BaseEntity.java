package com.microservices.accounts_service.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.microservices.accounts_service.audit.AuditAwareImpl;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@EntityListeners(AuditAwareImpl.class)
public class BaseEntity {
	
@CreatedDate
@Column(updatable = false)
private LocalDateTime createdAt;

@LastModifiedDate
@Column(insertable = false)
private LocalDateTime updateAt;

@CreatedBy
@Column(updatable = false)
private LocalDateTime createdBy;

@LastModifiedBy
@Column(insertable = false)
private LocalDateTime updatedBy;

}
