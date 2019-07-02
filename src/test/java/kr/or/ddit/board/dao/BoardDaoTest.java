package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class BoardDaoTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	@Resource(name="boardDao")
	private IboardDao boardDao;
	
	@Test
	public void boardListTest() {
		/***Given***/

		/***When***/
		List<BoardVo> boardList = boardDao.boardList();

		/***Then***/
		logger.debug("boardList111 :{}",boardList);
	}
	
	@Test
	public void boardUpdateTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo(7,"어쩌라고","Y");

		/***When***/
		int updateBoard = boardDao.updateBoardYN(boardVo);

		/***Then***/
		assertEquals(updateBoard, 1);
	}
	
	@Test
	public void boardInsertTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo("brown", "아 집가고 싶다", "Y");
		/***When***/
		int result = boardDao.insertBoard(boardVo);

		/***Then***/
		assertEquals(result, 1);
	}

}
