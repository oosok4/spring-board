package kr.or.ddit.comment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.comment.model.CommentVo;
import kr.or.ddit.comment.service.icomService;

@Controller
public class CommentController {
	
	@Resource(name="comService")
	private icomService comService;
	
	@RequestMapping("/comment")
	public String createComment(Model model,HttpSession session,String text_id,String userid,String com_content ) {
	
		int textId = Integer.parseInt(text_id); 
		
		CommentVo comVo = new CommentVo(textId, userid, com_content);
		comService.insertCom(comVo);
		
		model.addAttribute("text_id",textId);
		
		return "redirect:/text?textid="+text_id;
	}
	
}
