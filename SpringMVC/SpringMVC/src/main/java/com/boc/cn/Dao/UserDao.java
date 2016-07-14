package com.boc.cn.Dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.boc.cn.Service.DTO.UserDTO;

public interface UserDao {
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "username", column = "userName"),
			@Result(property = "password", column = "password"),
			@Result(property = "usertype", column = "userType")
			})
	@Select("select * from person where userName = #{userName}")
	public UserDTO getUser(String userName);
}
