package com.boc.cn.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boc.cn.Service.DTO.TransDTO;

public interface TransDao {
	// 交易信息
	@Select("SELECT * FROM trx WHERE contentId=#{id} AND personId=(CASE WHEN #{userId} is NULL then personId else #{userId} end) order by time desc")
	public List<TransDTO> getTrans(@Param("id")String id,@Param("userId")Integer userId);
}
