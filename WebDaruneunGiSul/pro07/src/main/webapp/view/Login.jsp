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
	<a href="./memberInsert">회원가입</a><br>
	<h1>로그인</h1>
	<form action="./login" method="post" >
		아이디:<input type="text" name="id" value ="${requestScope.id }"> <br>
		비밀번호:<input type="text" name="pwd"><br>
		아이디기억하기:<input type="checkbox" name="remember" checked="checked">
		${requestScope.msg }<br>
		<input type="submit" value="로그인">
		<input type="reset" value="초기화">
	</form>
	



</body>
</html>