package com.ashu.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Data
@Table(name="CourseDetails")
public class CourseEntity {

	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
}
