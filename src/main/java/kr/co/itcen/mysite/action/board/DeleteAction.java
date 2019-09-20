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

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long uNo = Long.parseLong(request.getParameter("uno")); // 게시글 만든 사용자 번호
		
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser"); // 로그인한 사용자 정보
		
		if (authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		System.out.println("1 : " + authUser.getNo());
		System.out.println("2 : " + uNo);
		
		if (authUser.getNo() != uNo) {
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		
		Long no = Long.parseLong(request.getParameter("no"));
		String password = (String)session.getAttribute(authUser.getPassword());
		
		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		boardVo.setPassword(password);
//		System.out.println(boardVo.getContents());
//		System.out.println(boardVo.getTitle());
//		System.out.println(boardVo.getuNo());
//
		new BoardDao().delete(boardVo);
		System.out.println("찍히냐");
//
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
		
		
		
	}

}
