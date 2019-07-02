package kr.or.ddit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.user.dao.IuserDao;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	@Resource(name="userDao")
	private IuserDao userDao;
	
	@Resource(name="boardDao")
	private IboardDao boardDao;
	/**
	 * 로그인한 사용자만 controller에 접근이 가능하도록 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		 HttpSession session  = request.getSession();
		 
		 if(session.getAttribute("USER_INFO")!=null)
			 return true;
				 
		 else {
			 response.sendRedirect(request.getContextPath()+"/login");
			 return false;
		 }
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.debug("userDao : {}",userDao);
		request.setAttribute("userList", userDao.userList());
		request.setAttribute("boardList", boardDao.boardList());
	}

}
