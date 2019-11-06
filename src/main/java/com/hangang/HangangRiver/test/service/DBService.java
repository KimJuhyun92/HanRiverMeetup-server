package com.hangang.HangangRiver.test.service;

import com.hangang.HangangRiver.test.dao.TestMapper;
import com.hangang.HangangRiver.test.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.test.dao.DBMapper;

import java.util.List;


@Service
public class DBService {

	@Autowired
	DBMapper dbMapper;
	
	public String getDual() throws Exception{
		return dbMapper.getDual();
	}

	@Autowired
	TestMapper testMapper;

	public List<TestModel> getAll() throws Exception{
		return testMapper.getAll();
	}


}
