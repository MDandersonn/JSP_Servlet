<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%String id= request.getParameter("id"); %>
<%String password= request.getParameter("password"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
JSP에서 받은 정보는 <br>
아이디: <%=id %> <br>
비밀번호: <%=password %>




</body>
</html>