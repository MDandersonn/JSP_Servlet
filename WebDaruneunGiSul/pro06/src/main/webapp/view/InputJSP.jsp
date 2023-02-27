<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="id" value= "${param.id}" />
<c:set var="password" value= "${param.password}" />
<%--<c:set var="subject" value= "<%=request.getParameterValues("subject") %>" /> --%>
<%String[] subject = request.getParameterValues("subject"); %>


<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<%=subject %>
	<br>
	<c:forEach var="data" items="<%=subject %>" >
		${data }<br>
	
	</c:forEach>
	

</body>
</html>