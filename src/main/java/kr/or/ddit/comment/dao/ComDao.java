package kr.or.ddit.comment.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.comment.model.CommentVo;

@Repository
public class ComDao implements IcomDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertCom(CommentVo comVo) {
		return sqlSession.insert("com.insertCom",comVo);
	}

	@Override
	public List<CommentVo> Comselect(int text_id) {
		return sqlSession.selectList("com.Comselect",text_id);
	}

	@Override
	public int changeCol(String com_id) {
		return 0;
	}
	

	
}
