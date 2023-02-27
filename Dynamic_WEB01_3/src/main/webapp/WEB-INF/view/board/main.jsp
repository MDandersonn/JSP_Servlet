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
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<a href="./">메인으로</a>
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
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="data" items="${requestScope.paging.data }">
					<tr>
						<td>${data.btype eq 'N' ? '공지' : data.id }</td>
						<td><a href="${boardUrl }/detail?id=${data.id }">${data.title }</a></td>
						<td>${data.writer }</td>
						<td>${data.createDate }</td>
						<td>${data.viewCnt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<ul class="pagination">
			<c:choose>
				<c:when test="${requestScope.paging.prevPageNumber eq -1 }">
					<li class="page-item disabled"><a class="page-link">prev</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardUrl }?p=${requestScope.paging.prevPageNumber }">prev</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="num" items="${requestScope.paging.pageList }">
				<li class="page-item ${requestScope.paging.currentPageNumber eq num ? 'active' : '' }"><a class="page-link" href="${boardUrl }?p=${num }">${num }</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${requestScope.paging.nextPageNumber eq -1 }">
					<li class="page-item disabled"><a class="page-link">next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${boardUrl }?p=${requestScope.paging.nextPageNumber }">next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>