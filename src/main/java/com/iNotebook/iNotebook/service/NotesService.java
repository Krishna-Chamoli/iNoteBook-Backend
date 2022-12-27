package com.iNotebook.iNotebook.service;

import java.util.List;
import java.util.Map;

import com.iNotebook.iNotebook.dto.NotesDto;
import com.iNotebook.iNotebook.exceptions.InternalException;
import com.iNotebook.iNotebook.exceptions.InvalidNoteException;

public interface NotesService {

	public NotesDto addNote(NotesDto note) throws InternalException;

	public NotesDto getNote(int noteId) throws InvalidNoteException, InternalException;

	public List<NotesDto> getNotes() throws InternalException;

	public NotesDto updateNote(NotesDto note) throws InvalidNoteException, InternalException;

	public Map<String, String> deleteNote(int noteId) throws InvalidNoteException, InternalException;

}
