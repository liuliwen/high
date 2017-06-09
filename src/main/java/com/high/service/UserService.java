package com.high.service;

import java.util.List;

import com.high.entity.User;
import com.high.exception.IdNullException;

public interface UserService {
	/**
	 * 
	 * @param user
	 */
	void insertUser(User user);
	List<User> selectAllUser();
	void addUser(User user);
	User login(User user) throws IdNullException;

	User findUserById(String userId);

    User registerAndLogin(User user) throws IdNullException;
}
