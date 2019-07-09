package kr.or.ddit.comment.service;

import java.util.List;

import kr.or.ddit.comment.model.CommentVo;


public interface icomService {
	
	/**
	* Method : insertCom
	* 작성자 : PC17
	* 변경이력 :
	* @param comVo
	* @return
	* Method 설명 : 댓글 등록
	*/
	int insertCom (CommentVo comVo);
	
	/**
	* Method : Comselect
	* 작성자 : PC17
	* 변경이력 :
	* @param text_id
	* @return
	* Method 설명 : 해당 게시물 번호에 있는 댓글 리스트 조회
	*/
	List<CommentVo> Comselect (int text_id);
	
	/**
	* Method : changeCol
	* 작성자 : PC17
	* 변경이력 :
	* @param com_id
	* @return
	* Method 설명 : 댓글 상태를 Y --> N으로 변경
	*/
	int changeCol (String com_id);
}
