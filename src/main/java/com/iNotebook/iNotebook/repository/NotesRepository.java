package com.iNotebook.iNotebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iNotebook.iNotebook.entity.NotesEntity;

public interface NotesRepository extends JpaRepository<NotesEntity, Integer> {

	List<NotesEntity> findByUserUserId(int userId);
}
