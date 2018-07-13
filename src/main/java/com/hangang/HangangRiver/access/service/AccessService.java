package com.hangang.HangangRiver.access.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.access.dao.AccessMapper;
import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;

@Service
public class AccessService {

	@Autowired
	AccessMapper accessMapper;

	public void createUser(User user){
    	accessMapper.insert(user);
	}

    public User getUserDetailById(String user_id){
    	return accessMapper.detail(user_id);
    }

    public void modifyUser(String user_id, User user){
    	accessMapper.update(user_id, user);
    }
}
