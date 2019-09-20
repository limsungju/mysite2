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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long no = Long.parseLong(request.getParameter("no"));
		Long uNo = Long.parseLong(request.getParameter("uno")); // 게시글 만든 사용자 번호

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser"); // 로그인한 사용자 정보
		System.out.println(authUser.getNo()); // 로그인한 사용자 번호
		System.out.println(uNo); // 게시글 만든 사용자 번호
		if (authUser.getNo() != uNo) {
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		System.out.println("넘어오냐아");
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setNo(no);
//		System.out.println(boardVo.getContents());
//		System.out.println(boardVo.getTitle());
//		System.out.println(boardVo.getuNo());
//
		new BoardDao().update(boardVo);
		System.out.println("찍히냐");
//
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
//
	}

}
