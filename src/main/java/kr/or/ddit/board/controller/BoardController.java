package kr.or.ddit.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.text.service.ItextService;
import kr.or.ddit.user.service.IuserService;

@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private IboardService boardService;
	
	@Resource(name="userService")
	private IuserService userService;
	
	@Resource(name="textService")
	private ItextService txtService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	
	
	@RequestMapping(path="/createBoard",method=RequestMethod.GET)
	public String createBoard(Model model, HttpSession session ) {
		if(session.getAttribute("USER_INFO")!=null) {
			model.addAttribute("boardList",boardService.boardList());
			model.addAttribute("boardAllList",boardService.boardAllList());
			return "tiles.createBoard";
		}else {
			return "login/login";
		}
	}
	
	@RequestMapping(path="/createBoard",method=RequestMethod.POST)
	public String createOrModify(Model model, HttpSession session,String boardName,String userId,String useYN,String board_id) {
		logger.debug("board_id : {}",board_id);
		logger.debug("boardName : {}",boardName);
		logger.debug("userId : {}",userId);
		logger.debug("status : {}",useYN);
		if(board_id!=null) {
			int boardId = Integer.parseInt(board_id);
			
			BoardVo boardVo = new BoardVo(boardId,boardName,useYN); 
			boardService.updateBoardYN(boardVo);
			logger.debug("boardService.updateBoardYN(boardVo) : {} ",boardService.updateBoardYN(boardVo));
			return "tiles.createBoard";
		}else {
			BoardVo boardVo = new BoardVo(userId,boardName,useYN);
			boardService.insertBoard(boardVo);
			model.addAttribute("boardList",boardService.boardList());
			return "tiles.main";
		}
	}
	
	@RequestMapping("/primaryBoard")
	public String selectBoard(Model model, HttpSession session, String board_id) {
		int boardId = Integer.parseInt(board_id);
		model.addAttribute("textList",txtService.allText(boardId));
		
		return "tiles.selectBoard";
	}
	
	
	
	

}
