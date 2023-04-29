package com.ashu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
