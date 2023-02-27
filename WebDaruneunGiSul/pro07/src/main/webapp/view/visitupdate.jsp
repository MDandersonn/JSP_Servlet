<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.dto.VisitDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록수정</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl}/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl}/bs5/js/bootstrap.bundle.min.js"></script> 
</head>
<body>
${staticUrl}
	<div>
		<a href="../visit">돌아가기</a>
	</div>
	
	
	<form action="./update" method="post">
		<div>
		<input type="hidden" name="id" value="<%=((VisitDTO)request.getAttribute("data")).getId()%>">
		</div>
		<div>
		<input type="text" name="nickname" value="<%=((VisitDTO)request.getAttribute("data")).getNickname()%>">
		</div>
		<div>
		<textarea rows="3" cols="25" name="context"><%=((VisitDTO)request.getAttribute("data")).getContext()%></textarea>
		</div>
		<div>
		<input type="submit">저장
		</div>
	</form>

</body>
</html>