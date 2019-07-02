package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class BoardServiceTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
	
	@Resource
	private IboardService boardService;

	@Test
	public void boardListTest() {
		/***Given***/

		/***When***/
		List<BoardVo>boardList =boardService.boardList();
		/***Then***/
		logger.debug("serviceList :{}",boardList);
		
	}

}
