package com.iNotebook.iNotebook.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iNotebook.iNotebook.dao.UserDao;
import com.iNotebook.iNotebook.entity.UserEntity;
import com.iNotebook.iNotebook.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserEntity addUser(UserEntity user) {
		UserEntity userEntity = userRepo.save(user);
		return userEntity;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		UserEntity user = userRepo.findByEmail(email);
		return user;
	}
}
