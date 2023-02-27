<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 에러</title>
</head>
<body>
	<h1>파일 업로드 처리중 저장 경로에 문제가 발생하였습니다.</h1>
	<%=exception.getMessage() %><br>
	<%=exception.toString() %><br>
	<%=exception.getCause().getClass() %><br>
</body>
</html>