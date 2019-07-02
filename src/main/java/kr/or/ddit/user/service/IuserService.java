package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVo;

public interface IuserService {
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :전체유저 조회
	 */
	List<UserVo> userList();

	/**
	 * 
	* Method : getUser
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :사용자 정보 조회
	 */
	UserVo getUser(String userId);
	
	Map<String, Object> textPagingList(Map<String, Object> map);
	
	
	
	
	
	
}
