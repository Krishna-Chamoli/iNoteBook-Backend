package com.iNotebook.iNotebook.service;

import com.iNotebook.iNotebook.dto.UserAddDto;
import com.iNotebook.iNotebook.dto.UserViewDto;
import com.iNotebook.iNotebook.exceptions.DuplicateUserException;

public interface UserService {

	public UserViewDto addUser(UserAddDto user) throws DuplicateUserException;

	public UserViewDto getUserByEmail(String email);

	public UserViewDto getUser();
}
