package com.hangang.HangangRiver.access.dao;

import org.apache.ibatis.annotations.Param;

import com.hangang.HangangRiver.access.model.User;


public interface AccessMapper {
	public void insert(User user);
	public void update(@Param("user_id") String user_id,
			@Param("user") User user);
	public User detail(String user_id);
}
