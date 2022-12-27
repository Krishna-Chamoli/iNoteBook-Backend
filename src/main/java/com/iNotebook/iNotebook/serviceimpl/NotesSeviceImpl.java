package com.iNotebook.iNotebook.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.iNotebook.iNotebook.dao.NotesDao;
import com.iNotebook.iNotebook.dao.UserDao;
import com.iNotebook.iNotebook.dto.NotesDto;
import com.iNotebook.iNotebook.entity.NotesEntity;
import com.iNotebook.iNotebook.entity.UserEntity;
import com.iNotebook.iNotebook.exceptions.InternalException;
import com.iNotebook.iNotebook.exceptions.InvalidNoteException;
import com.iNotebook.iNotebook.service.NotesService;

@Service
public class NotesSeviceImpl implements NotesService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	NotesDao notesDao;

	@Autowired
	UserDao userDao;

	@Override
	public NotesDto addNote(NotesDto noteDto) throws InternalException {
		try {

			String userName = this.getLoggedInUserName();

			UserEntity userEntity = userDao.getUserByEmail(userName);
			NotesEntity noteEntity = this.convertNotesDtoToEntity(noteDto);
			noteEntity.setUser(userEntity);

			noteEntity.setDate(new Date());

			NotesDto noteDtoFinal = this.convertNotesEntityToDto(notesDao.addNote(noteEntity));
			return noteDtoFinal;

		} catch (InternalServerError e) {
			throw new InternalException();
		}

	}

	@Override
	public NotesDto getNote(int noteId) throws InvalidNoteException, InternalException {
		try {

			String userName = this.getLoggedInUserName();

			NotesEntity noteEntity = notesDao.getNote(noteId);
			if (noteEntity != null && noteEntity.getUser().getEmail().equals(userName)) {
				NotesDto noteDto = this.convertNotesEntityToDto(noteEntity);
				return noteDto;
			} else {
				throw new InvalidNoteException();
			}

		} catch (InternalServerError e) {
			throw new InternalException();
		}

	}

	@Override
	public List<NotesDto> getNotes() throws InternalException {
		try {

			String userName = this.getLoggedInUserName();

			int userId = userDao.getUserByEmail(userName).getUserId();

			List<NotesDto> notesDtoList = notesDao.getNotes(userId).stream().map(this::convertNotesEntityToDto)
					.collect(Collectors.toList());

			return notesDtoList;

		} catch (InternalServerError e) {
			throw new InternalException();
		}

	}

	@Override
	public NotesDto updateNote(NotesDto note) throws InvalidNoteException, InternalException {
		try {
			String userName = this.getLoggedInUserName();

			NotesEntity noteEntity = this.convertNotesDtoToEntity(note);

			NotesEntity checkNote = notesDao.getNote(noteEntity.getNotesId());

			if (checkNote == null || checkNote.getUser().getEmail().equals(userName)) {
				UserEntity userEntity = userDao.getUserByEmail(userName);
				noteEntity.setUser(userEntity);
				noteEntity.setDate(new Date());
				NotesDto noteDto = this.convertNotesEntityToDto(notesDao.updateNote(noteEntity));
				return noteDto;
			} else {
				throw new InvalidNoteException();
			}
		} catch (InternalServerError e) {
			throw new InternalException();
		}

	}

	@Override
	public Map<String, String> deleteNote(int noteId) throws InvalidNoteException, InternalException {
		try {
			String userName = this.getLoggedInUserName();

			NotesEntity noteEntity = notesDao.getNote(noteId);

			if (noteEntity != null && noteEntity.getUser().getEmail().equals(userName)) {
				Map<String, String> response = new HashMap<>();
				boolean isDeleted = notesDao.deleteNote(noteId);
				if (isDeleted) {
					response.put("deleted", "yes");
				} else {
					response.put("deleted", "no");
				}
				return response;
			} else {
				throw new InvalidNoteException();
			}
		} catch (InternalServerError e) {
			throw new InternalException();
		}

	}

	private NotesEntity convertNotesDtoToEntity(NotesDto noteDto) {
		NotesEntity noteEntity = modelMapper.map(noteDto, NotesEntity.class);
		return noteEntity;
	}

	private NotesDto convertNotesEntityToDto(NotesEntity noteEntity) {
		NotesDto noteDto = modelMapper.map(noteEntity, NotesDto.class);
		return noteDto;
	}

	private String getLoggedInUserName() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

}
