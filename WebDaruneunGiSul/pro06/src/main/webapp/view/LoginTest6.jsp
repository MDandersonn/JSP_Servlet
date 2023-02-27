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
<body>
	<form name ="Flogin" action="/k1" method="post" encType="UTF-8">
		아이디<input type="text" name= "id"><br>
		비밀번호<input type= "password" name="password"><br>
		<input type="submit" value="로그인"><input type="reset" value="되돌리기"> 

	</form>
</body>
</html>