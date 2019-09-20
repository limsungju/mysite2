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

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		HttpSession session = request.getSession();
		
		if (session == null) { // 비로그인이면 게시판 리스트로 돌리기
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser"); // 로그인한 사용자 정보
		
		if (authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		
		
		
		System.out.println(no);
		BoardVo boardVo = new BoardDao().getList(no);
		request.setAttribute("boardVo", boardVo);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/delete.jsp");
	}

}
