<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="pagination">
	<c:choose>
		<c:when test="${requestScope.paging.prevPageNumber eq -1 }">
			<li class="page-item disabled"><a class="page-link">prev</a></li>
		</c:when>
		<c:otherwise>
		<%-- <c:set var="pagingUrl" value="${visitUrl }" /> 라고 각각 jsp마다 설정해놓는다. --%>
			<li class="page-item"><a class="page-link" href="${pagingUrl }?p=${requestScope.paging.prevPageNumber }">prev</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="num" items="${requestScope.paging.pageList }">
		<li class="page-item ${requestScope.paging.currentPageNumber eq num ? 'active' : '' }"><a class="page-link" href="${pagingUrl }?p=${num }">${num }</a></li>
	</c:forEach>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageNumber eq -1 }">
			<li class="page-item disabled"><a class="page-link">next</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="${pagingUrl }?p=${requestScope.paging.nextPageNumber }">next</a></li>
		</c:otherwise>
	</c:choose>
</ul>