<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<c:url var="boardUrl" value="/board" />
	<div>
		<h1>게시글 목록 페이지</h1>
	</div>
	<div>
		<form action="${boardUrl }">
			<select name="c" onchange="submit();">
				<c:forEach var="size" begin="5" end="30" step="5">
					<option value="${size }" ${requestScope.paging.pageLimit eq size ? "selected" : "" }>${size }개</option>
				</c:forEach>
			</select>
		</form>
	</div>
	<c:if test="${sessionScope.login }">
		<div>
			<button class="btn btn-primary" onclick="location.href='${boardUrl }/add'">글작성</button>
		</div>
	</c:if>
	<div>
		<form action="${boardUrl }/delete" method="post">
			<button type="submit">삭제</button>
			<table>
				<thead>
					<tr>
						<th></th>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${requestScope.paging.data }">
						<tr>
							<td><input type="checkbox" name="chk_id" value="${data.id }"></td>
							<td>${data.btype eq 'N' ? '공지' : data.id }</td>
							<td><a href="${boardUrl }/detail?id=${data.id }">${data.title }</a></td>
							<td>${data.writer }</td>
							<td>${data.createDate }</td>
							<td>${data.viewCnt }</td>
							<td><a href="${boardUrl }/delete?id=${data.id }">삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div>
		<c:set var="pagingUrl" value="${boardUrl }" />
		<%@ include file="/WEB-INF/view/module/paging.jsp" %>
	</div>
</body>
</html>