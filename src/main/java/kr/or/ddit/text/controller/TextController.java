package kr.or.ddit.text.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.comment.model.CommentVo;
import kr.or.ddit.comment.service.icomService;
import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.file.service.IfileService;
import kr.or.ddit.text.model.TextVo;
import kr.or.ddit.text.service.ItextService;
import kr.or.ddit.util.PartUtil;

@Controller
public class TextController {
	
	private static final Logger logger = LoggerFactory.getLogger(TextController.class);
	
	@Resource(name="boardService")
	private IboardService boardService;
	
	@Resource(name="textService")
	private ItextService txtService;
	
	@Resource(name="fileService")
	private IfileService fileService;
	
	@Resource(name="comService")
	private icomService comService;
	
	@RequestMapping(path="/createText",method=RequestMethod.GET)
	public String clickCreateText(Model model,HttpSession session,String board_id,String userid) {
		int boardId = Integer.parseInt(board_id);
		//session.setAttribute("userid", userid);
		userid = (String) session.getAttribute("userid");
		logger.debug("userid : {}",userid);
		
		if(userid!=""){
			boardService.getBoard(boardId);
			return "board/createText";
		}else {
			return "login/login"; 
		}
	}
	
	@RequestMapping(path="/createText",method=RequestMethod.POST)
	public String createText(HttpServletRequest request,Model model,TextVo textVo,String title,String smarteditor,
			HttpSession session,String userid, @RequestPart MultipartFile[] profile ) throws IOException, ServletException {
		int boardId = textVo.getBoard_id();
		int text_id = textVo.getText_id();
		System.out.println("**********************************");
		logger.debug("userid : {}", userid);
		logger.debug("board_id : {}", boardId);
		logger.debug("text_id : {}", text_id);
		
		logger.debug("title : {}", title);
		logger.debug("smarteditor : {}", smarteditor);
		System.out.println("**********************************");
		TextVo textVo2 = new TextVo(boardId, title, smarteditor, userid);
		
		txtService.insertText(textVo2);
		
		int textid = txtService.recentReply().getText_id();
//		String textid = String.valueOf(textId);
//		logger.debug("textid--------- : {}",textid);
		model.addAttribute("textid",textid);
	
		int count = 0;
		
		int cnt = 0;
		
		for(MultipartFile f : profile) {
			logger.debug("여기까지 들어오니?");
			if(f.getSize() > 0) {
				count++;
				String fileName = f.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String uploadPath = PartUtil.getUploadPath();
				String path = uploadPath + File.separator+UUID.randomUUID();
				
				File uploadFile = new File(path);
				
				try {
					f.transferTo(uploadFile);
				}catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				FileVo fileVo = new FileVo(textid,path,fileName);
				
				cnt += fileService.insertFile(fileVo);
				
			}
		}
		if(cnt==count) {
			logger.debug("업로드완료");
		}
		int text_id1 = txtService.recentReply().getText_id();
		
			
		return "redirect:/text?textid="+text_id1;
		
		
	}
	
	@RequestMapping("/text")
	public String ViewText(Model model, HttpSession session, int textid) {

		logger.debug("textid-- : {}",textid);
		
		model.addAttribute("textVo", txtService.getText(textid));
		
//		int textId = Integer.parseInt(textid);
		
//		if(textid < txtService.recentReply().getText_id()) {
//			TextVo textVo = txtService.getText(textid);
//			model.addAttribute("textVo", textVo);
//		}else {
//			textid = txtService.recentReply().getText_id();
//			TextVo textVo = txtService.getText(textid);
//			model.addAttribute("textVo", textVo);
//		}
		logger.debug("textId:{}",textid);
		List<CommentVo> comList = comService.Comselect(textid);
		
		List<FileVo> fileList = fileService.fileList(textid);
		logger.debug("-------------------------------------------------fileList:{}",fileList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("comList", comList);
		
		
		logger.debug("textid : {}",textid);
		
		return "tiles.textView";
	}
	
	@RequestMapping(path="/reply",method=RequestMethod.GET)
	public String reply(Model model,HttpSession session,String text_id,String board_id,String userid,String group_num) {
		int textId = Integer.parseInt(text_id);
		int boardId = Integer.parseInt(board_id);
		int groupNum = Integer.parseInt(group_num);
		
		model.addAttribute("text_id",textId);
		model.addAttribute("board_id",boardId);
		model.addAttribute("userid",userid);
		model.addAttribute("group_num",groupNum);
		
		TextVo textVo = new TextVo(textId, boardId, userid, groupNum);
		
		session.setAttribute("REPLY_INFO", textVo);
		
		
		return "board/reply";
	}
	
	@RequestMapping(path="/reply",method=RequestMethod.POST)
	public String createReply(Model model, HttpSession session,String userid,
			String title,String smarteditor,@RequestPart MultipartFile[] profile) {
	
		TextVo textVo = (TextVo) session.getAttribute("REPLY_INFO");
		int board_id =textVo.getBoard_id();
		int text_id = textVo.getText_id();
		int group_num = textVo.getGroup_num();
		
		String Content = String.valueOf(smarteditor);
		String text_title = String.valueOf(title);
		String user_id = String.valueOf(userid);
		user_id = (String) session.getAttribute("userid");
		
		
		TextVo textVo2 = new TextVo(text_id, board_id, text_title, Content, user_id, group_num);
		txtService.insertReply(textVo2);
		TextVo text = txtService.recentReply();
		int text1 = text.getText_id();
		int count = 0;
		
		int cnt = 0;
		
		for(MultipartFile f : profile) {
			logger.debug("여기까지 들어오니?");
			if(f.getSize() > 0) {
				count++;
				String fileName = f.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String uploadPath = PartUtil.getUploadPath();
				String path = uploadPath + File.separator+UUID.randomUUID();
				
				File uploadFile = new File(path);
				
				try {
					f.transferTo(uploadFile);
				}catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				FileVo fileVo = new FileVo(text1,path,fileName);
				
				cnt += fileService.insertFile(fileVo);
				
			}
		}
		if(cnt==count) {
			logger.debug("업로드완료");
		}
		int text_id1 = txtService.recentReply().getText_id();
		
		return "redirect:/text?textid="+text_id1;
	}
	
	@RequestMapping(path="/modify",method=RequestMethod.GET)
	public String modify(Model model, HttpSession session,String text_id) {
		int textId = Integer.parseInt(text_id);
		
		TextVo textVo = txtService.getText(textId);
		
		List<FileVo> fileList = fileService.fileList(textId);
		model.addAttribute("fileList", fileList);
		model.addAttribute("text_id", text_id);
		model.addAttribute("textVo", textVo);
		
		return "board/modify";
	}
	
	@RequestMapping(path="/modify",method=RequestMethod.POST)
	public String modifyPlay(Model model,HttpServletRequest request ,HttpSession session,String board_id
			,String userid,String text,String smarteditor
			,String text_id,@RequestPart MultipartFile[] profile) throws IOException, ServletException {
		
		int boardId = Integer.parseInt(board_id);
		int textId = Integer.parseInt(text_id);
		
		int count = 0;
		
		int cnt = 0;
		
		for(MultipartFile f : profile) {
			logger.debug("여기까지 들어오니?");
			if(f.getSize() > 0) {
				count++;
				String fileName = f.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String uploadPath = PartUtil.getUploadPath();
				String path = uploadPath + File.separator+UUID.randomUUID();
				
				File uploadFile = new File(path);
				
				try {
					f.transferTo(uploadFile);
				}catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				FileVo fileVo = new FileVo(textId,path,fileName);
				
				cnt += fileService.insertFile(fileVo);
				
			}
		}
		if(cnt==count) {
			logger.debug("업로드완료");
		}
		int text_id1 = txtService.recentReply().getText_id();
		
		return "redirect:/text?textid="+ text_id;
	}
	
	@RequestMapping("/delete")
	public String delete(String text_id) {
		
		txtService.changeCol(text_id);
		
		return "tiles.delete";
	}

}
