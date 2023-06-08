package com.aza.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aza.dao.TestBoardMapper;
import com.aza.domain.Test;
import com.aza.domain.TestBoard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestService {

	private final TestBoardMapper testBoardMapper;
	
	public Test getTest() {
		Test test = new Test();
		test.setTestName("test1");
		test.setTestContent("Controller connect test");
		test.setCaretedDate(new Date());
		return test;
	}
	
	public List<TestBoard> getAllBoard(){
		return testBoardMapper.getBoardList();
	}
}
