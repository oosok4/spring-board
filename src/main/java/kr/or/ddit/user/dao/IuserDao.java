package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.text.model.TextVo;
import kr.or.ddit.user.model.UserVo;

public interface IuserDao {
	
	
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
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
	
	/**
	 * 
	* Method : textPagingList
	* 작성자 : PC17
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 페이징 리스트 만들기
	 */
	List<TextVo> textPagingList(Map<String, Object> map);
	
	/**
	* Method : textCnt
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 전체수 조회
	*/
	int textCnt(int board_id);
	
	

}
