package com.os.beans.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8132124731461162085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	@NonNull
	private Long id;

	@Version
	@Column(name="VERSION",nullable = false)
	private Integer version = 0;
}