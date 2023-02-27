<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String id = (String)request.getParameter("id1"); %>
<% String password = (String)request.getParameter("password1"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${ (id.length() !=0) && (password.length() !=0 )}">
	!!!!<br>
	아이디: <%=id %> 님 로그인 하셨습니다. <br>
	비밀번호 : <%=password %><br>
</c:if>

<c:choose>
	<c:when test="${ (id.length() ==0) || (password.length() ==0 )}">
		아이디 또는 패스워드를 입력하세요
		<a href="/pro06/test01/login.html">로그인창으로 이동</a>
	</c:when>
</c:choose>
</body>
</html>