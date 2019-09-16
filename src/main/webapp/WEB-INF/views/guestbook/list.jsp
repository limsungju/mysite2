<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.itcen.mysite.dao.GuestbookDao" %>
<%@ page import="kr.co.itcen.mysite.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>
<%
	List<GuestbookVo> list = new GuestbookDao().getList();
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath() %>/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<%
							int count = list.size();
							int index = 0;
							for(GuestbookVo guestVo : list) {
						%>
						<br>
							<table width=510 border=1>
								<tr>
									<td><%= count-index++ %></td>
									<td><%= guestVo.getName() %></td>
									<td><%= guestVo.getRegDate() %></td>
									<td><a href="<%=request.getContextPath() %>/guestbook?a=deleteform&no=<%=guestVo.getNo()%>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4><%= guestVo.getContents().replaceAll("\n", "<br>") %></td>
								</tr>
							</table>
						<%
							}
						%>
						<br>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>