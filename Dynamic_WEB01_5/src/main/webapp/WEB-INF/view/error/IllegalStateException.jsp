<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 에러</title>
</head>
<body>
	<h1>파일의 크기가 너무 큽니다.</h1>
	<%=exception.getMessage() %><br>
	<%=exception.toString() %><br>
	<%=exception.getCause().getClass() %><br>
</body>
</html>