package com.high.service.impl;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.high.entity.User;
import com.high.exception.IdNullException;
import com.high.mapper.UserMapper;
import com.high.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public List<User> selectAllUser() {
		return userMapper.selectAllUser();
	}

	
	@Override
	public void addUser(User user) {
		if (null == user.getUserId()) {
			UUID uuid = UUID.randomUUID();
			String userId = uuid.toString();
			System.out.println("userId: " + userId);
			user.setUserId(userId);
		}
		userMapper.insertUser(user);
	}

	/**
	 * 微信小程序 获得微信id，若改id还未注册，则注册并登录用户。若已注册，则登录。若没有id，则异常
	 * @throws IdNullException 
	 */
	@Override
	public User login(User user) throws IdNullException {
		String userId = user.getUserId();
		if(userId != null){
			User findUser = userMapper.findUserById(userId);
			return findUser;
		}else{
			throw new IdNullException("用户id为空");
		}
	}
	@Override
	public User findUserById(String userId) {
		return userMapper.findUserById(userId);
	}

	@Override
	public User registerAndLogin(User user) throws IdNullException {
		String userId = user.getUserId();
		if(userId != null){
			User findUser = userMapper.findUserById(userId);
			if(user ==null){
				userMapper.insertUser(user);
			}
			return findUser;
		}else{
			throw new IdNullException("用户id为空");
		}
	}
}
