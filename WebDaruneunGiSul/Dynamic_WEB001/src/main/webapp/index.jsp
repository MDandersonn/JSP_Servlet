<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.dto.VisitDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈화면</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl}/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl}/bs5/js/bootstrap.bundle.min.js"></script> 
</head>
<body>
	<c:url var="mainUrl" value="/" />
	<h1>hello jsp/servlet (index.jsp 파일입니다.)</h1>
	
	<c:choose>
		<c:when test="${sessionScope.login}"><!-- 로그인이 되어있으면 -->
			<a href="${mainUrl }logout">로그아웃</a>
			<a href="${mainUrl }myinfo">개인정보</a>
		</c:when>
		<c:otherwise><!-- 로그인이 되어있지않으면 -->
			<a href="${mainUrl}login">로그인</a>
			<a href="${mainUrl}join">회원가입</a>
		</c:otherwise>
	</c:choose>
	<br><br><br>
	상대경로사용:
	<a href="./visit">방명록</a>
	<a href="./bookmark"> 즐겨찾기</a>
	<br><br><br>
	c:url(JSTL)사용 : 
	<a href="${mainUrl}visit">방명록</a>
	<a href="${mainUrl}bookmark"> 즐겨찾기</a>
	
</body>
</html>
