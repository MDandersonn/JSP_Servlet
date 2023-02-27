<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<c:url var="boardUrl" value="/board" />
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div>
		<h1>${requestScope.data.title }</h1>
	</div>
	<div>
		<fmt:formatDate var="createDate" type="both" pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초" value="${requestScope.data.createDate }" />
		<fmt:formatDate var="updateDate" type="both" pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초" value="${requestScope.data.updateDate }" />
		작성자: ${requestScope.data.writer }<br>
		작성일: ${createDate }<br>
		수정일: ${updateDate }<br>
		조회수: ${requestScope.data.viewCnt }
	</div>
	<div>
		<p>${requestScope.data.context }</p>
	</div>
	<div>
		<ul>
			<c:forEach var="image" items="${requestScope.images }" >
				<c:url var="imagePath" value="${image.path }${image.uuid }" />
				<%--realPath/uuid에서 이미지를 받아옴  path경로를 이렇게 url태그에 넣으면 realPath가된다.--%>
				<%--download="${image.name } 다운로드받을때 파일명설정 --%>
				<li><a href="${imagePath }" download="${image.name }">${image.name }</a></li>
				<li><img src="${imagePath }" width="200px;" height="200;"></li>
			</c:forEach>
		</ul>
	</div>
	<div>
	<%--필터를 이용해 로그인한 사용자만 추천,비추천누를수 있게 해주었다. --%> 
		<button type="button" class="btn btn-primary" onclick="location.href='${boardUrl }/rec?id=${requestScope.data.id }'">
			추천 ${requestScope.data.recCnt }</button>
		<button type="button" class="btn btn-danger" onclick="location.href='${boardUrl }/nrec?id=${requestScope.data.id }'">
			비추천 ${requestScope.data.nRecCnt }</button>
	</div>
	<div>
		<button onclick="location.href='${boardUrl }'">목록</button>
		<c:if test="${sessionScope.login }">
			<c:if test="${sessionScope.user.userId eq requestScope.data.writer }">
				<button onclick="location.href='${boardUrl }/update?id=${requestScope.data.id }'">수정</button>
				<button onclick="location.href='${boardUrl }/delete?id=${requestScope.data.id }'">삭제</button>
			</c:if>
		</c:if>
	</div>
</body>
</html>