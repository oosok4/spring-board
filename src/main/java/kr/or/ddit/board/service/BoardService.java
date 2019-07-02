package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.model.BoardVo;

@Service
public class BoardService implements IboardService {

	@Resource(name = "boardDao")
	private IboardDao boardDao;

	@Override
	public List<BoardVo> boardList() {
		return boardDao.boardList();
	}

	@Override
	public List<BoardVo> boardAllList() {
		return boardDao.boardAllList();
	}

	@Override
	public int updateBoardYN(BoardVo boardVo) {
		return boardDao.updateBoardYN(boardVo);
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}
	
	

	

}
