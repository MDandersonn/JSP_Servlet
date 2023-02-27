<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈 화면</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>  
<%-- <jsp:include page="/WEB-INF/view/module/bootstrap.jsp"/> 
이걸로하면 <c: ~를 인식을 못해서 경로가 제대로 인식안됨
--%> 
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<h1>Hello JSP/Servlet</h1>

	
</body>
</html>