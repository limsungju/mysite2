<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form action="${pageContext.servletContext.contextPath }/board" method="post" >
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="${boardVo.no }">
					<input type='hidden' name="uno" value="${boardVo.uNo }">
					<label>게시글을 지우시려면 <p>삭제하기</p>를 입력해주세요</label> <br>
					<input type="text" name="text" value="">
					<input type="submit" value="확인">
				</form>
				<a href="${pageContext.servletContext.contextPath }/board?a=list">게시판 리스트</a>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>