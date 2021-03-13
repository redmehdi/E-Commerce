package com.red.one.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.persistence.Id;

/**
 * @author Redouane Mehdi
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String modifiedBy;
	@CreatedDate LocalDateTime createdDate;
	@LastModifiedDate LocalDateTime modifiedDate;
}
