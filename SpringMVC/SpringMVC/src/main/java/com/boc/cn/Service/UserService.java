package com.boc.cn.Service;

import org.springframework.stereotype.Service;

import com.boc.cn.Service.DTO.UserDTO;

@Service
public interface UserService {
	
	public UserDTO getUser(String userName); 

}
