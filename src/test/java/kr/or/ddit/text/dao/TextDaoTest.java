package kr.or.ddit.text.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.text.model.TextVo;

public class TextDaoTest extends LogicTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(TextDaoTest.class);
	
	@Resource(name="textDao")
	private ItextDao textDao;

	

	@Test
	public void textListTest() {
		/***Given***/

		int board_id = 5;
		/***When***/
		List<TextVo> textList = textDao.allText(board_id);

		/***Then***/
		logger.debug("textList111 : {}",textList);
		
	}

}
