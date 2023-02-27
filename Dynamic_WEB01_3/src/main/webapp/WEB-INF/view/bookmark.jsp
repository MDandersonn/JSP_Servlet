<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div>
		<c:url var="mainUrl" value="/" />
		<a href="${mainUrl }">메인으로</a>
	</div>
	<h2>즐겨찾기</h2>
	<c:url var="bookmarkUrl" value="/bookmark" />
	<form action="${bookmarkUrl }" method="post">
		<div>
			<input type="text" name="url">
		</div>
		<div>
			<input type="text" name="name">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
	<div>
		<form action="${bookmarkUrl }">
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
			<c:url var="bookmarkUpdateUrl" value="/bookmark/update">
				<c:param name="id" value="${d.id }" />
			</c:url>
			<c:set var="formId" value="deleteForm${d.id }" />
			<li>
				<a href="${d.url }">${d.name }</a>
				<button type="button" onclick="location.href='${bookmarkUpdateUrl }'">수정</button>
				<button type="submit" form="${formId }">삭제</button>
				<form id="${formId }" action="${bookmarkUrl }/delete" method="post">
					<input type="hidden" name="id" value="${d.id }">
				</form>
			</li>
		</c:forEach>
	</ul>
	<ul class="pagination">
		<c:set var="pageNumber" value="${empty param.p ? 1 : param.p }" />
		<c:choose>
			<c:when test="${requestScope.paging.prevPageNumber eq -1 }">
				<li class="page-item disabled"><a class="page-link">prev</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${bookmarkUrl }?p=${requestScope.paging.prevPageNumber }">prev</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="num" items="${requestScope.paging.pageList }">
			<li class="page-item ${requestScope.paging.currentPageNumber eq num ? 'active' : '' }"><a class="page-link" href="${bookmarkUrl }?p=${num }">${num }</a></li>
		</c:forEach>
		<c:choose>
			<c:when test="${requestScope.paging.nextPageNumber eq -1 }">
				<li class="page-item disabled"><a class="page-link">next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${bookmarkUrl }?p=${requestScope.paging.nextPageNumber }">next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>