package com.ashu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {
	
	public UserDtlsEntity findByEmail(String email);
	public UserDtlsEntity findByEmailAndPassword(String email, String password);

}
