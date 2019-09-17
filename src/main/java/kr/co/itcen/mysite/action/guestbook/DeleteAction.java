package kr.co.itcen.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");

		GuestbookVo guestVo = new GuestbookVo();
		guestVo.setNo(Long.parseLong(no));
		guestVo.setPassword(password);

		new GuestbookDao().delete(guestVo);

		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook?a=list");

	}

}
