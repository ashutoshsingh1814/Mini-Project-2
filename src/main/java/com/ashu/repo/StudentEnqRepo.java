package com.ashu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

}
