package com.iNotebook.iNotebook.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iNotebook.iNotebook.dao.NotesDao;
import com.iNotebook.iNotebook.entity.NotesEntity;
import com.iNotebook.iNotebook.repository.NotesRepository;

@Repository
public class NotesDaoImpl implements NotesDao {

	@Autowired
	NotesRepository notesRepo;

	@Override
	public NotesEntity addNote(NotesEntity note) {
		NotesEntity noteEntity = notesRepo.save(note);
		return noteEntity;
	}

	@Override
	public NotesEntity getNote(int noteId) {
		NotesEntity note = notesRepo.findById(noteId).orElse(null);
		return note;
	}

	@Override
	public List<NotesEntity> getNotes(int userId) {
		List<NotesEntity> notesList = notesRepo.findByUserUserId(userId);
		return notesList;
	}

	@Override
	public NotesEntity updateNote(NotesEntity note) {
		NotesEntity noteEntity = notesRepo.save(note);
		return noteEntity;
	}

	@Override
	public boolean deleteNote(int noteId) {
		NotesEntity note = notesRepo.findById(noteId).orElse(null);
		if (note != null) {
			notesRepo.delete(note);
			return true;
		}
		return false;
	}

}
