package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.PaginationUtil;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("kwd");
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt((pageStr == null || pageStr.length() == 0) ? "1" : pageStr);
		
		BoardDao dao = new BoardDao();
		
		int totalCnt = dao.getListCount(keyword); // 전체 게시글 수
		PaginationUtil pagination = new PaginationUtil(page, totalCnt, 10, 5);
		
		List<BoardVo> list = dao.getList(keyword, pagination);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		
		
//		1. dao에서 검색 조건을 포함한 게시글의 총 수를 구한다
//		select count(*) ~~~
		
//		2. paginationUtil 생성
//		PaginationUtil pUtil = new PaginationUtil(/*현재페이지, 게시물의 총 개수, 한 페이지에 보여줄 게시글의 수, 페이지 블럭에 보여줄 페이지의 수*/);
		
//		3.검색시 페이징유틸 포함 쿼리 실행
//		List<BoardVo> list = new BoardDao().getAllList(kwd, pUtil);
		
//		4.DAO안에서 쿼리수정

//		6. 마지막으로 pUtil, keyword, search request에 포함
//		뷰(jsp에서 꺼내서 셋팅)
		
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
