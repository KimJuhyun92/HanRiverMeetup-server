package com.hangang.HangangRiver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.service.DBService;


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

}
