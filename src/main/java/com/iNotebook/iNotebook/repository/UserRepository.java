package com.iNotebook.iNotebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iNotebook.iNotebook.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);
}