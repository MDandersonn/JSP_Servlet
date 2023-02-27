<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl }" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="userId" value="${cookie.remember.value }">
			<c:if test="${not empty requestScope.error }">
				<span style="color: red;">${requestScope.error }</span>
			</c:if>
		</div>
		<div>
			<label>패스워드</label>
			<input type="password" name="password">
		</div>
		<div>
			<label>아이디 기억하기</label>
			<c:choose>
				<c:when test="${empty requestScope.remember }">
					<input type="checkbox" name="remember">
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="remember" checked>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>
</body>
</html>