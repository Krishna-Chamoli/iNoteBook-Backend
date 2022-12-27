package com.iNotebook.iNotebook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iNotebook.iNotebook.dto.UserAddDto;
import com.iNotebook.iNotebook.dto.UserViewDto;
import com.iNotebook.iNotebook.exceptions.DuplicateUserException;
import com.iNotebook.iNotebook.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<UserViewDto> addUser(@Valid @RequestBody UserAddDto user) throws DuplicateUserException {
		UserViewDto userView = userService.addUser(user);
		return new ResponseEntity<>(userView, HttpStatus.CREATED);
	}

	@GetMapping("/getuser")
	public ResponseEntity<UserViewDto> getUser() {
		UserViewDto userView = userService.getUser();
		return new ResponseEntity<>(userView, HttpStatus.OK);
	}
}
