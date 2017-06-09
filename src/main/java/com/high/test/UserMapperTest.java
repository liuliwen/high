package com.high.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.high.entity.User;
import com.high.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void demo1(){
		String id = "c939e479-197d-4aae-b9c9-6dc02203850f";
		User user = userMapper.findUserById(id);
		System.out.println(user.getUserId());
	}
}
