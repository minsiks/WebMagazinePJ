package com.aza.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.aza.domain.TestBoard;

@Mapper
@Repository
public interface TestBoardMapper {

	public List<TestBoard> getBoardList();
}
