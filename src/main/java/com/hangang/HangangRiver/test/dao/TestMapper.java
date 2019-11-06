package com.hangang.HangangRiver.test.dao;

import com.hangang.HangangRiver.test.model.TestModel;

import java.util.List;

public interface TestMapper {
    List<TestModel> getAll() throws Exception;
}
