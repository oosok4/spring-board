package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IuserService userService;
	
	@Resource(name= "boardService")
	private IboardService boardService;
		
	/**
	 * 
	* Method : loginProcess
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :사용자 로그인 화면
	 */
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String loginView(HttpSession session ) {
		if(session.getAttribute("USER_INFO") != null) {
			
			return "tiles.main";
		}else
			return "login/login";
	}
	
	/**
	 * 
	* Method : loginProcess
	* 작성자 : PC17
	* 변경이력 :
	* @return
	* Method 설명 :사용자 로그인 요청 처리
	 */
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String loginProcess(String userId, String password,String rememberme,
								HttpServletResponse response, HttpSession session) {
		String encryptPassword = KISA_SHA256.encrypt(password);
		UserVo userVo = userService.getUser(userId);
		
		if(userVo != null&&encryptPassword.equals(userVo.getPass())) {
			rememberme(userId, rememberme, response);
			
			session.setAttribute("USER_INFO", userVo);
			session.setAttribute("boardList", boardService.boardList());
			return "tiles.main";
		}
		else 
			return "login/login";
	
	}
	
	/**
	 * 
	* Method : rememberme
	* 작성자 : PC17
	* 변경이력 :
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 :rememberme 쿠키를 응답으로 생성
	 */
	private void rememberme(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge = 0;
		if (rememberme !=null)
			cookieMaxAge = 60*60*24*30;
		
		Cookie userIdCookie = new Cookie("userId",userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie rememberCookie = new Cookie("rememberme", "true");
		rememberCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(userIdCookie);
		response.addCookie(rememberCookie);
	}
}



		
