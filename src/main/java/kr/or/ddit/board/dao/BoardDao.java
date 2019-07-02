package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;

@Repository
public class BoardDao implements IboardDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	 * y인항목만
	 */
	@Override
	public List<BoardVo> boardList() {
		return sqlSession.selectList("board.boardList");
	}
	
	/**
	 * 전체
	 */
	@Override
	public List<BoardVo> boardAllList() {
		return sqlSession.selectList("board.boardAllList");
	}

	@Override
	public int updateBoardYN(BoardVo boardVo) {
		return sqlSession.update("board.updateBoardYN",boardVo);
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return sqlSession.insert("board.insertBoard",boardVo);
	}
	
	
	

	

}
