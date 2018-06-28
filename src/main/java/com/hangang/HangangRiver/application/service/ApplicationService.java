package com.hangang.HangangRiver.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.application.dao.ApplicationMapper;
import com.hangang.HangangRiver.application.model.Application;

@Service
public class ApplicationService {
	@Autowired
	ApplicationMapper applicationMapper;

	public void save(Application application){
		applicationMapper.insert(application);
	}

	public void remove(int application_seq){
		applicationMapper.delete(application_seq);
	}
}
