package kr.co.itcen.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");

		GuestbookVo guesetVo = new GuestbookVo();
		guesetVo.setName(name);
		guesetVo.setPassword(password);
		guesetVo.setContents(contents);

		new GuestbookDao().insert(guesetVo);

		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook?a=list");

	}

}
