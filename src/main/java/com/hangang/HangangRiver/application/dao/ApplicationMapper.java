package com.hangang.HangangRiver.application.dao;

import java.util.List;

import com.hangang.HangangRiver.application.model.Application;
public interface ApplicationMapper {
	public void insert(Application application);
	public void delete(int application_seq);
	public List<Application> getApplications(int meeting_seq);
}
