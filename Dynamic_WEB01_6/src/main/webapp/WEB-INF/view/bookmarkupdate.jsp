<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기 수정</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<h2>즐겨찾기 수정</h2>
		<c:url var="bookmarkUrl" value="/bookmark" />
	<form action="${bookmarkUrl }/update" method="post">
		<div>
			<input type="hidden" name="id" value="${requestScope.data.id }">
		</div>
		<div>
			<input type="text" name="url" value="${requestScope.data.url }">
		</div>
		<div>
			<input type="text" name="name" value="${requestScope.data.name }">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>