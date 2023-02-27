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
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl }" method="post">
	<!-- ${sessionScope.sessionName} -->
		<div>
			<label>아이디</label>
			<!--  req.setAttribute("remember", c.getValue());로 받은거 초기속성값으로 적용-->
			<!--  <input type="text" name="userId" value="${requestScope.remember }">
				이렇게해도되고 아래처럼해도됨. 쿠키객체 사용하는 방법이다.
			-->
			<input type="text" name="userId" value="${cookie.remember.value }">
			<c:if test="${not empty requestScope.error }">
				<span style="color:red;">${requestScope.error }</span>
			</c:if>
		</div>
		<div>
			<label>패스워드</label>
			<input type ="password" name= "password">
		</div>
		<div>
			<label>아이디 기억하기</label>
			<c:choose>
				<c:when test="${empty requestScope.remember }">
				<%-- 이전에 체크가 되어있지 않을때 --%>
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