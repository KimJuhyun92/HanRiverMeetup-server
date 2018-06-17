package com.hangang.HangangRiver.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.test.dao.DBMapper;




@Service
public class DBService {

	@Autowired
	DBMapper dbMapper;
	
	public String getDual() throws Exception{
		return dbMapper.getDual();
	}

}
