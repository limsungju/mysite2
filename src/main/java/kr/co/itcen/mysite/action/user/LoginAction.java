package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo userVo = new UserDao().get(email, password);
		
		if(userVo == null) { // null이면 다시 로그인폼으로 돌려준다.
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return; // 코드를 안끊어주면 제어권은 jsp에게 넘어가지만 코드가 실행되기 때문에 exception이 날 수도 있기 때문에 꼭 끊어주어야 한다.
		}
		
		// 인증처리 (Session 처리)
		HttpSession session = request.getSession(true); // false를 넣어주면 없으면 null을 리턴, true를 넣어주면 없으면 만들어서 보내준다.
		session.setAttribute("authUser", userVo); // 인증할 name, 넣어줄 객체
		
		WebUtils.redirect(request, response, request.getContextPath()); // main으로 돌리기
		
	}

}
