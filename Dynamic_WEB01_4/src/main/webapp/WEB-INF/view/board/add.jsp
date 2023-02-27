<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<script type="text/javascript">
		function imageValid(element) {
			if(element.files.length > 3) {
				alert('이미지는 3개 까지만 선택할 수 있습니다.');
				console.log(element.files);
				element.value = "";//선택된 파일들 초기화시켜줌
				console.log(element.files);
			}
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div>
		<h1>글 작성</h1>
	</div>
	<div>
		<c:url var="boardAddUrl" value="/board/add" />
		<form action="${boardAddUrl }" method="post" enctype="multipart/form-data">
			<div>
				<label>제목</label>
				<input type="text" name="title">
			</div>
			<div>
				<label>내용</label>
				<textarea name="context"></textarea>
			</div>
			<div>
				<label>이미지 업로드</label>
				<input type="file" onchange="imageValid(this);" name="imageUpload" accept="image/*"  multiple>
			</div>
			<c:if test="${sessionScope.role.type eq 'ADMIN' }">
				<div>
					<label>공지사항</label>
					<input type="checkbox" name="notice" value="yes">
				</div>
			</c:if>
			<div>
				<button type="submit">저장</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
</body>
</html>