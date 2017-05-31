package com.os.beans.entities;

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
	
	private Integer version;

	@Version
	public Integer getVersion(){
		return version;
	};

	public void setVersion(Integer version){
		this.version = version;
	};
}