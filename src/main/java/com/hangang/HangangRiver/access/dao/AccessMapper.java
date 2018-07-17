package com.hangang.HangangRiver.access.dao;

import org.apache.ibatis.annotations.Param;

import com.hangang.HangangRiver.access.model.User;


public interface AccessMapper {
	void insert(User user);
	void update(@Param("user_id") String user_id,
			@Param("user") User user);
	User detail(String user_id);
	boolean isExistNickname(String nickname);
}
