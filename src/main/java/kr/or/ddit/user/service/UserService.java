package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements IuserService {

	
	@Resource(name="userDao")
	private IuserDao userDao;
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :전체유저 조회
	 */
	@Override
	public List<UserVo> userList() {
		return userDao.userList();
	}

	

	/**
	 * 
	* Method : getUser
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :사용자 정보 조회
	 */
	@Override
	public UserVo getUser(String userId) {
		return userDao.getUser(userId);
	}



	@Override
	public Map<String, Object> textPagingList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("textList", userDao.textPagingList(map));
		String board_id2 = String.valueOf(map.get("board_id"));
		int board_id = Integer.parseInt(board_id2);
		int textCnt = userDao.textCnt(board_id);
		int paginationSize = (int) Math.ceil((double) textCnt
				/ (int) map.get("pageSize"));
		resultMap.put("paginationSize", paginationSize);
		return resultMap;
	}

}
