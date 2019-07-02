package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import kr.or.ddit.text.model.TextVo;
import kr.or.ddit.text.service.ItextService;
import kr.or.ddit.user.model.PageVo;
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
			session.setAttribute("boardList",boardService.boardList());
			session.setAttribute("boardAllList",boardService.boardAllList());
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
			session.setAttribute("boardAllList",boardService.boardAllList());
			session.setAttribute("boardList",boardService.boardList());
			return "tiles.createBoard";
		}else {
			BoardVo boardVo = new BoardVo(userId,boardName,useYN);
			boardService.insertBoard(boardVo);
			session.setAttribute("boardList",boardService.boardList());
			return "tiles.main";
		}
	}
	
	@RequestMapping("/primaryBoard")
	public String selectBoard(Model model, HttpSession session, String board_id,String page,String pageSize) {
		int boardId = Integer.parseInt(board_id);
		session.setAttribute("board_id",boardId);
		model.addAttribute("textList",txtService.allText(boardId));
		
		BoardVo boardVo = boardService.getBoard(boardId);
		
		int pageInt = page == null ? 1 :Integer.parseInt(page); 
		int pageSizeInt = pageSize  == null ? 10 : Integer.parseInt(pageSize);
		PageVo pageVo = new PageVo(pageInt, pageSizeInt);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageInt);
		map.put("pageSize", pageSizeInt);
		map.put("board_id", board_id);
		
		logger.debug("map : {}",map);
		
		Map<String, Object> resultMap = userService.textPagingList(map);
		int paginationSize = (Integer)resultMap.get("paginationSize");
		List<TextVo> textList = (List<TextVo>) resultMap.get("textList");
		
		
		session.setAttribute("boardList", boardService.boardList());
		
		model.addAttribute("boardVo",boardVo);
		model.addAttribute("textList",textList);
		model.addAttribute("pageVo",pageVo);
		model.addAttribute("paginationSize",paginationSize);
		
		return "tiles.selectBoard";
	}
	
	
	
	

}
