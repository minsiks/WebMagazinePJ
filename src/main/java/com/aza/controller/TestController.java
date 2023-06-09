package com.aza.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aza.domain.Test;
import com.aza.domain.TestBoard;
import com.aza.service.TestService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final TestService testService;
	
	@GetMapping("/info")
	public Object test1() {
		log.debug("/info start");
		
		Test test = testService.getTest();
		
		log.info("return {}", test.toString());
		return test;
	}
	@GetMapping("/info2")
	public Object test2() {
		JsonObject jo = new JsonObject();
		
		jo.addProperty("projectName", "wemagazine");
		jo.addProperty("testContent", "GSonTest");
		jo.addProperty("createdDate", new Date().toString());
		
		JsonArray ja = new JsonArray();
		for(int i=0; i<5; i++) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("prop"+i, i);
			ja.add(jObj);
		}
		jo.add("follwer", ja);
		
		return jo.toString();//
	}
	
	@GetMapping("/myBatisTest")
	public List<TestBoard> getBoardTest(){
		return testService.getAllBoard();
	}

}
