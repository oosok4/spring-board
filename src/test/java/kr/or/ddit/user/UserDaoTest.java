package kr.or.ddit.user;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends LogicTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Resource(name="userDao")
	private IuserDao userDao;

	@Test
	public void userListTest() {
		/***Given***/
		
		

		/***When***/
		List<UserVo> userList = userDao.userList();

		/***Then***/
		logger.debug("userList : {}",userList);
	}
	
	@Test
	public void getUser() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		logger.debug("userVo : {}",userVo);
		
	}

}
