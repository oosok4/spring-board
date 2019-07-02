package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface IboardDao {
	
	
	List<BoardVo> boardList();
	
	List<BoardVo> boardAllList();
	
	/**
	* Method : updateBoardY
	* 작성자 : PC17
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 사용여부 설정
	*/
	int updateBoardYN (BoardVo boardVo);
	
	/**
	* Method : insertBoard
	* 작성자 : PC17
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 등록
	*/
	int insertBoard(BoardVo boardVo);
	
	
}
