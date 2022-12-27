package com.iNotebook.iNotebook.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iNotebook.iNotebook.dto.NotesDto;
import com.iNotebook.iNotebook.exceptions.InternalException;
import com.iNotebook.iNotebook.exceptions.InvalidNoteException;
import com.iNotebook.iNotebook.service.NotesService;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

	@Autowired
	NotesService notesService;

	@PostMapping("/addnote")
	public ResponseEntity<NotesDto> addNote(@Valid @RequestBody NotesDto noteDto) throws InternalException {
		NotesDto note = notesService.addNote(noteDto);
		return new ResponseEntity<>(note, HttpStatus.CREATED);
	}

	@GetMapping("/getnote")
	public ResponseEntity<NotesDto> getNote(@Valid @RequestParam int noteId)
			throws InvalidNoteException, InternalException {
		NotesDto note = notesService.getNote(noteId);
		return new ResponseEntity<>(note, HttpStatus.OK);
	}

	@GetMapping("/getnotes")
	public ResponseEntity<List<NotesDto>> getNotes() throws InternalException {
		List<NotesDto> notesDtoList = notesService.getNotes();
		return new ResponseEntity<>(notesDtoList, HttpStatus.OK);
	}

	@PutMapping("/updatenote")
	public ResponseEntity<NotesDto> updateNote(@Valid @RequestBody NotesDto noteDto)
			throws InvalidNoteException, InternalException {
		NotesDto note = notesService.updateNote(noteDto);
		return new ResponseEntity<>(note, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletenote")
	public ResponseEntity<Map<String, String>> deleteNote(@Valid @RequestParam int noteId)
			throws InvalidNoteException, InternalException {
		Map<String, String> response = notesService.deleteNote(noteId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
