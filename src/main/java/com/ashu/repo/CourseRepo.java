package com.ashu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

}
