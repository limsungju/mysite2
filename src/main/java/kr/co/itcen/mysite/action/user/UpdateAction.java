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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}

		UserVo updateUser = (UserVo) session.getAttribute("updateUser");
		System.out.println(updateUser);
		if (updateUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		UserVo userVo = new UserVo();
		userVo.setNo(updateUser.getNo());
		userVo.setName(name);
		userVo.setGender(gender);
		
		if("".equals(password)) {
			userVo.setPassword(updateUser.getPassword());
		}else {
			userVo.setPassword(password);
		}
		new UserDao().update(userVo);
		
		session.setAttribute("authUser", userVo);
		WebUtils.redirect(request, response, request.getContextPath()); // main으로 돌리기
		
	}

}
