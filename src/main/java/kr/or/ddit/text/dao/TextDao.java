package kr.or.ddit.text.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.text.model.TextVo;

@Repository
public class TextDao implements ItextDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	

	@Override
	public int insertText(TextVo textVo) {
		
		return sqlSession.insert("text.insertText",textVo);
	}

	//게시판안에 모든 게시글 가져오기
	@Override
	public List<TextVo> allText(int board_id) {
		
		return sqlSession.selectList("text.allText",board_id);
	}

	//게시글 하나 조회
	@Override
	public TextVo getText(int text_id) {
		
		return sqlSession.selectOne("text.getText",text_id);
	}

	@Override
	public int updateText(TextVo textVo) {
		
		return sqlSession.update("text.updateText",textVo);
	}

	@Override
	public int changeCol(String text_id) {
		
		return sqlSession.update("text.changeCol",text_id);
	}

	@Override
	public int insertReply(TextVo textVo) {
		
		return sqlSession.insert("text.insertReply",textVo);
	}

	@Override
	public TextVo recentReply() {
		
		return sqlSession.selectOne("text.recentReply");
	}
}
