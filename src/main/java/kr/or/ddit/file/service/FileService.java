package kr.or.ddit.file.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.file.dao.IfileDao;
import kr.or.ddit.file.model.FileVo;

@Service
public class FileService implements IfileService {

	@Resource(name="fileDao")
	private IfileDao fileDao;
	
	
	@Override
	public int insertFile(FileVo fileVo) {
		return fileDao.insertFile(fileVo);
	}

	@Override
	public List<FileVo> fileList(int text_id) {
		return fileDao.fileList(text_id);
	}

	@Override
	public FileVo getfile(int file_id) {
		return fileDao.getfile(file_id);
	}

	@Override
	public int del(int file_id) {
		return fileDao.del(file_id);
	}

}
