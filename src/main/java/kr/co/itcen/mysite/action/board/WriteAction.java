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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 되어 있는 사용자 인지 판별
		HttpSession session = request.getSession(false);
		if (session == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println(authUser);
		if (authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		Long no = null;
		
		if(!"".equals(request.getParameter("no"))) {
			no = Long.parseLong(request.getParameter("no"));
		}
			
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setoNo(1);
		boardVo.setDepth(0);
		boardVo.setuNo(authUser.getNo()); // 로그인 되어 있는 사용자 번호
		
		System.out.println(no);
		
		if(no == null) { 
			new BoardDao().insert(boardVo);
			
		}else {
			boardVo.setNo(no);
			BoardVo selectBVo = new BoardDao().select(no);
			
			Integer gNo = selectBVo.getgNo();
			Integer oNo = selectBVo.getoNo()+1;
			Integer depth = selectBVo.getDepth()+1;
			
			boardVo.setgNo(gNo);
			boardVo.setoNo(oNo);
			boardVo.setDepth(depth);
			new BoardDao().update(gNo, oNo);
			new BoardDao().insertBoard(boardVo);
			
		}

		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");

	}

}
