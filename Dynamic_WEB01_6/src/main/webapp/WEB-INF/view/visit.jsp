<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<h2>방명록</h2>
	<c:url var="visitUrl" value="/visit" />
	<form action="${visitUrl }" method="post">
		<div>
			<input type="text" name="nickname">
		</div>
		<div>
			<textarea rows="3" cols="25" name="context"></textarea>
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
	<div>
		<form action="${visitUrl }">
			<select name="c" onchange="submit();">
				<option value="5" ${requestScope.paging.pageLimit eq 5 ? "selected" : "" }>5개</option>
				<option value="10" ${requestScope.paging.pageLimit eq 10 ? "selected" : "" }>10개</option>
				<option value="15" ${requestScope.paging.pageLimit eq 15 ? "selected" : "" }>15개</option>
				<option value="20" ${requestScope.paging.pageLimit eq 20 ? "selected" : "" }>20개</option>
				<option value="25" ${requestScope.paging.pageLimit eq 25 ? "selected" : "" }>25개</option>
			</select>
		</form>
	</div>
	<ul>
		<c:forEach var="d" items="${requestScope.paging.data }">
			<c:url var="visitUpdateUrl" value="/visit/update">
				<c:param name="id" value="${d.id }" />
			</c:url>
			<c:set var="formId" value="deleteForm${d.id }" />
			<li>
				${d.nickname } | ${d.context }
				<button type="button" onclick="location.href='${visitUpdateUrl }'">수정</button>
				<button type="submit" form="${formId }">삭제</button>
				<form id="${formId }" action="${visitUrl }/delete" method="post">
					<input type="hidden" name="id" value="${d.id}">
				</form>
			</li>
		</c:forEach>
	</ul>
	<c:set var="pagingUrl" value="${visitUrl }" />
	<%@ include file="/WEB-INF/view/module/paging.jsp" %>
</body>
</html>