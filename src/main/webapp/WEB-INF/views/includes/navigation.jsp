<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div id="navigation">
	<ul>
		<li><a href="${pageContext.servletContext.contextPath }">임성주</a></li>
		<li><a href="${pageContext.servletContext.contextPath }/guestbook?a=list">방명록</a></li>
		<li><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
	</ul>
</div>