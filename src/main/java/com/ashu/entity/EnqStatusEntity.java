package com.ashu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "EnqStatusDetails")
public class EnqStatusEntity {
	
	@Id
	@GeneratedValue
	private Integer enqStatusId;
	private String enqStatus;

}
