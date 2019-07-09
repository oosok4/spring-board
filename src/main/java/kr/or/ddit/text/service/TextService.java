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
		return txtDao.insertText(textVo);
	}

	@Override
	public List<TextVo> allText(int board_id) {
		
		return txtDao.allText(board_id);
	}

	@Override
	public TextVo getText(int text_id) {
		return txtDao.getText(text_id);
	}

	@Override
	public int updateText(TextVo textVo) {
		return txtDao.updateText(textVo);
	}

	@Override
	public int changeCol(String text_id) {
		return txtDao.changeCol(text_id);
	}

	@Override
	public int insertReply(TextVo textVo) {
		return txtDao.insertReply(textVo);
	}

	@Override
	public TextVo recentReply() {
		return txtDao.recentReply();
	}

}
