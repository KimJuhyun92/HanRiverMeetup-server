package com.hangang.HangangRiver.test.web;

import com.hangang.HangangRiver.test.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.test.service.DBService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DBController {
	@Autowired
	DBService dbService;

	@RequestMapping("/hello")
	public @ResponseBody String root_test() throws Exception {
		return "Hello World";
	}

	@RequestMapping("/now")
	public @ResponseBody String now() throws Exception {
		System.out.println("/now");
			return dbService.getDual();

	}

    @RequestMapping("/getAll")
    public @ResponseBody
    List<TestModel> getAll() throws Exception {
        System.out.println("test success!");

        List<TestModel> list = new ArrayList<TestModel>();
        list = dbService.getAll();

        return list;
    }

}
