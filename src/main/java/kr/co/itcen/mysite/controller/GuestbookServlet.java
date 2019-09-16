package kr.co.itcen.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.web.WebUtils;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");

			GuestbookVo guesetVo = new GuestbookVo();
			guesetVo.setName(name);
			guesetVo.setPassword(password);
			guesetVo.setContents(contents);

			new GuestbookDao().insert(guesetVo);

			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook/");
		} else if ("deleteform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		} else if ("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			GuestbookVo guestVo = new GuestbookVo();
			guestVo.setNo(Long.parseLong(no));
			guestVo.setPassword(password);
			
			new GuestbookDao().delete(guestVo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
		} else {
			WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
