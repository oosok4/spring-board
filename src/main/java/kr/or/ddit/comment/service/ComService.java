package kr.or.ddit.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.comment.dao.IcomDao;
import kr.or.ddit.comment.model.CommentVo;

@Service
public class ComService implements icomService {

	@Resource(name="comDao")
	private IcomDao comDao;
	
	
	@Override
	public int insertCom(CommentVo comVo) {
		return comDao.insertCom(comVo);
	}

	@Override
	public List<CommentVo> Comselect(int text_id) {
		return comDao.Comselect(text_id);
	}

	@Override
	public int changeCol(String com_id) {
		return comDao.changeCol(com_id);
	}

}
