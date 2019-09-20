package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));

		BoardVo boardVo = new BoardDao().getList(no);
		request.setAttribute("boardVo", boardVo);

		HttpSession session = request.getSession();
		if (session == null) {
			WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
			return;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
			return;
		}
		request.setAttribute("authUser", authUser);

		System.out.println("로그인사용자번호" + boardVo.getuNo());
		System.out.println("게시판 만든사람 번호" + authUser.getNo());

		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");

	}

}
