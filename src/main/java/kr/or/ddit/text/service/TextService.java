package kr.or.ddit.text.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.text.dao.ItextDao;
import kr.or.ddit.text.model.TextVo;

@Service
public class TextService implements ItextService {
	
	@Resource(name="textDao")
	private ItextDao txtDao;

	@Override
	public int insertText(TextVo textVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TextVo> allText(int board_id) {
		
		return txtDao.allText(board_id);
	}

	@Override
	public TextVo getText(int text_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateText(TextVo textVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeCol(String text_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReply(TextVo textVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TextVo recentReply() {
		// TODO Auto-generated method stub
		return null;
	}

}
