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
	<ul>
		<c:forEach var="d" items="${requestScope.data }">
			<c:url var="bookmarkUpdateUrl" value="/bookmark/update">
				<c:param name="id" value="${d.id }" />
			</c:url>
			<%-- <c:set var="formId" value="deleteForm${d.id }" />--%>
			<li>
				<a href="${d.url }">${d.name }</a>
				<button type="button" onclick="location.href='${bookmarkUpdateUrl }'">수정</button>
				<button type="submit" form="DeleteForm${d.id }">삭제</button>
				<%--<button type="submit" form="DeleteForm" > 으로 하면 이것을 눌렀을때
				해당 삭제버튼의  <form id="DeleteForm" action="${bookmarkUrl }/delete" method="post">가
				작동하는게아니라 맨위에있는 삭제버튼의 <form id="DeleteForm" action="${bookmarkUrl }/delete" method="post">
				가 작동해서 맨위에꺼 부터 지워진다.
				
				즉, 같은 id의 form태그가 여러개가 존재하기때문에 form태그별로 다른id를 써야한다.
				 --%>
				<form id="DeleteForm${d.id }" action="${bookmarkUrl }/delete" method="post">
					<input type="hidden" name="id" value="${d.id }"> 
				</form>
				
				<%--
				<button type="submit" form="${formId }">삭제</button>
				<form id="${formId }" action="${bookmarkUrl }/delete" method="post">
					<input type="hidden" name="id" value="${d.id }">
				</form>
				--%>
			</li>
		</c:forEach>
	</ul>
</body>
</html>