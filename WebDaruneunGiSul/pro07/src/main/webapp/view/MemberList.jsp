<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	
</style>
<body>
	<a href="./">메인으로</a>
	<a href="./memberInsert">회원추가</a><br>
	<h1>회원명단</h1>
	
	<form action="./search" method="get">
		이름 : <input type="text" name="name">
		<input type="submit" value="검색">
	</form>
	<br>
	<table border="1" style="border-collapse:collapse;">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="data" items="${requestScope.data }">
			<tr>
				<td>${data.id }</td>
				<td>${data.pwd }</td>
				<td>${data.name }</td>
				<td>${data.email }</td>
				<td>${data.joinDate }</td>
				
				<td><button onclick="location.href='./memberUpdate?id=${data.id}'">수정</button></td>
				<td><button  onclick="location.href='./memberDelete?id=${data.id}'" >삭제</button></td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>