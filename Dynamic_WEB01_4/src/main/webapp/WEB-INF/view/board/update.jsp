<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div>
		<h1>글 수정 - ${requestScope.data.title }</h1>
	</div>
	<div>
		<c:url var="boardUpdateUrl" value="/board/update" />
		<form action="${boardUpdateUrl }" method="post">
			<input type="hidden" name="id" value="${requestScope.data.id }">
			<div>
				<label>제목</label>
				<input type="text" name="title" value="${requestScope.data.title }">
			</div>
			<div>
				<label>내용</label>
				<textarea name="context">${requestScope.data.context }</textarea>
			</div>
			<div>
				<button type="submit">수정</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
</body>
</html>