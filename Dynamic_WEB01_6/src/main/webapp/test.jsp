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
	<form action="./test">
		<input type="text" id="username" name="username">
		<input type="checkbox" id="music" name="interest" value="music"><label for="music">음악</label>
		<input type="checkbox" id="book" name="interest" value="book"><label for="book">도서</label>
		<input type="checkbox" id="movie" name="interest" value="movie"><label for="movie">영화</label>
		<input type="submit">
	</form>
	
	<hr>
	<form action="./login.do" method="post">
		<input type="text" name="id">
		<input type="password" name="pw">
		<input type="submit" value="login">
	</form>

</body>
</html>