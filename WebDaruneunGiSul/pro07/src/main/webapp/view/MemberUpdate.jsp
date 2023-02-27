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
	<a href="./memberList">회원명단보기</a><br>
	<h1>회원정보수정</h1>
	<form action="./memberUpdate" method="post">
		<input type="hidden" name="id" value="${data.id }" />
		비밀번호:<input type="text" name="pwd" value="${data.pwd }"/><br>
		이름:<input type="text" name="name" value="${data.name }"/><br>
		email:<input type="text" name="email" value="${data.email }"/><br>
		<input type="submit" value="수정하기">
		
	</form>
	
	<form action="./upload" method="post" enctype="multipart/form-data">
	파일1: <input type="file" name="file1"><br>
	파일2: <input type="file" name="file2"><br>
	매개변수1: <input type="text" name="param1"><br>
	매개변수2: <input type="text" name="param2"><br>
	매개변수3: <input type="text" name="param3"><br>
	<input type="submit" value="업로드">
	
	
	</form>


</body>
</html>