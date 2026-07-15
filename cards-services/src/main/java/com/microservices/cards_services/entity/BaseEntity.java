package com.microservices.cards_services.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.microservices.cards_services.audit.AuditAwareImpl;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@EntityListeners(AuditAwareImpl.class)
public class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@CreatedBy
	@Column(updatable = false)
	private LocalDateTime createdBy;
	
	@LastModifiedBy
	@Column(insertable = false)
	private LocalDateTime updatedBy;
	
	@LastModifiedDate
	@Column(updatable = false)
	private LocalDateTime updatedAt;
}
