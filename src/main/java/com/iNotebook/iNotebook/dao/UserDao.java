package com.iNotebook.iNotebook.dao;

import com.iNotebook.iNotebook.entity.UserEntity;

public interface UserDao {

	public UserEntity addUser(UserEntity user);

	public UserEntity getUserByEmail(String email);

}
