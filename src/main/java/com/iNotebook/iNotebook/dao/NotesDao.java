package com.iNotebook.iNotebook.dao;

import java.util.List;

import com.iNotebook.iNotebook.entity.NotesEntity;

public interface NotesDao {
	public NotesEntity addNote(NotesEntity note);

	public NotesEntity getNote(int noteId);

	public List<NotesEntity> getNotes(int userId);

	public NotesEntity updateNote(NotesEntity note);

	public boolean deleteNote(int noteId);
}
