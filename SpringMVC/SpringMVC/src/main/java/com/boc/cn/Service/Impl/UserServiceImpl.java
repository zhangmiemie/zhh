package com.boc.cn.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.cn.Dao.UserDao;
import com.boc.cn.Service.UserService;
import com.boc.cn.Service.DTO.UserDTO;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public UserDTO getUser(String userName) {
		UserDTO user = null;
		user = userDao.getUser(userName);	
		return user;
	}

}
