package com.iNotebook.iNotebook.serviceimpl;

import java.security.SecureRandom;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iNotebook.iNotebook.dao.UserDao;
import com.iNotebook.iNotebook.dto.UserAddDto;
import com.iNotebook.iNotebook.dto.UserViewDto;
import com.iNotebook.iNotebook.entity.UserEntity;
import com.iNotebook.iNotebook.exceptions.DuplicateUserException;
import com.iNotebook.iNotebook.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserDao userDao;

	@Override
	public UserViewDto addUser(UserAddDto user) throws DuplicateUserException {
		UserEntity userEntity = this.convertUserAddDtoToEntity(user);
		if (userDao.getUserByEmail(userEntity.getEmail()) != null) {
			throw new DuplicateUserException();
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
		String encodedPassword = encoder.encode(userEntity.getPassword());
		userEntity.setPassword(encodedPassword);
		UserViewDto userView = this.convertUserEntityToUserViewDto(userDao.addUser(userEntity));
		return userView;
	}

	@Override
	public UserViewDto getUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserViewDto userView = this.getUserByEmail(userDetails.getUsername());
		return userView;
	}

	@Override
	public UserViewDto getUserByEmail(String email) {
		UserEntity user = userDao.getUserByEmail(email);
		UserViewDto userView = this.convertUserEntityToUserViewDto(user);
		return userView;
	}

	private UserEntity convertUserAddDtoToEntity(UserAddDto user) {
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		return userEntity;
	}

	private UserViewDto convertUserEntityToUserViewDto(UserEntity userEntity) {
		UserViewDto user = modelMapper.map(userEntity, UserViewDto.class);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userDao.getUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
		}
	}

}
