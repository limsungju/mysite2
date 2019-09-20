package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));

		System.out.println(no);
		BoardVo boardVo = new BoardDao().getList(no);
		request.setAttribute("boardVo", boardVo);
//		System.out.println("게시판 번호1" + no);
//		System.out.println("게시판 번호2" + boardVo.getNo());
//		System.out.println("유저 번호" + boardVo.getuNo());
//		System.out.println("게시글 내용" + boardVo.getContents());
//		System.out.println("게시글 제목" + boardVo.getTitle());

		WebUtils.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}

}
