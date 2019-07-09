package kr.or.ddit.file.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.file.model.FileVo;

@Repository
public class FileDao implements IfileDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertFile(FileVo fileVo) {
		return sqlSession.insert("file.insertFile",fileVo);
	}

	@Override
	public List<FileVo> fileList(int text_id) {
		return sqlSession.selectList("file.fileList",text_id);
	}

	@Override
	public FileVo getfile(int file_id) {
		return sqlSession.selectOne("file.getfile",file_id);
	}

	@Override
	public int del(int file_id) {
		return 0;
	}

}
