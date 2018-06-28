package com.hangang.HangangRiver.application.dao;

import com.hangang.HangangRiver.application.model.Application;

public interface ApplicationMapper {
	public void insert(Application application);
	public void delete(int application_seq);
}
