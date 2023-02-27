<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="paging.Paging" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div>
		<c:url var="mainUrl" value="/" />
		<a href="${mainUrl }">메인으로</a>
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
			<!-- value의 속성값은 html에서 " "안으로 넣어야함 그리고 문자숫자개념이아님
			서버로 전송하면 아무리숫자여도 무조건 문자열로 전송되고 처리됨 -->
				<option value="5" ${requestScope.paging.pageLimit eq 5 ? "selected":"" }>5개</option>
				<option value="10" ${requestScope.paging.pageLimit eq 10 ? "selected":"" }>10개</option>
				<option value="15" ${requestScope.paging.pageLimit eq 15 ? "selected":"" }>15개</option>
				<option value="20" ${requestScope.paging.pageLimit eq 20 ? "selected":"" }>20개</option>
				<option value="25" ${requestScope.paging.pageLimit eq 25 ? "selected":"" }>25개</option>
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
	<ul class="pagination">
		<%--<c:set var="pageNumber" value="${empty param.p ? 1 :param.p}" /> 이건이제 필요없다.--%>
		<%= ((Paging)request.getAttribute("paging")).getPrevPageNumber() %>   <%-- 이러면 출력이 잘된다 .--%> 
		<%--.getprevPageNumber() --%>
		${requestScope.paging}
		${requestScope.paging.paa}<br>
		
		<%--
		${requestScope.paging.t1}<br>에러
		${requestScope.paging.t2}<br>에러
		결론: 멤버변수를 가져오는게아니라 게터에있는 메서드리턴값을 가져오는거였구나.
		 --%>
		<%-- get을빼고 맨앞소문자로바꿔서 메서드명을 적으면 return값을 반환하네.  --%>
		<%--${requestScope.paging.Paa}<br>에러
		${requestScope.paging.pAA}<br>에러
		${requestScope.paging.getPaa}<br>에러
		${requestScope.paging.getpaa}<br>에러--%>
		<c:choose>
			<c:when test="${requestScope.paging.prevPageNumber eq -1 }">
			<%--이렇게  get빼고 메서드명(첫소문자) 써주면 메서드가 작동되서 return값이 반환되나? 응--%>
				<li class="page-item disabled"><a class="page-link">prev</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="./visit?p=${requestScope.paging.prevPageNumber }">prev</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="num" items="${requestScope.paging.pageList}">
			<li class="page-item ${requestScope.paging.currentPageNumber eq num ? 'active' : '' }"><a class="page-link" href="${visitUrl }?p=${num }">${num }</a></li>
		</c:forEach>

		<!--  마지막페이지번호몇번? : 전체행수/목록수 몫이얼마냐 
			나머지가 있냐없냐에따라서/
		-->
		<c:choose>
			<c:when test="${requestScope.paging.nextPageNumber eq -1}">
				<li class="page-item disabled"><a class="page-link">next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="./visit?p=${requestScope.paging.nextPageNumber }">next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>