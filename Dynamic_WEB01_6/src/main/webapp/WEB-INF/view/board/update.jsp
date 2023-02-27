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
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
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
				<div id="editor"></div>
				<textarea style="display: none;" name="context"></textarea>
			</div>
			<div>
				<button type="submit">수정</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var editor;
		window.onload = function() {
			<c:url var="boardDetailUrl" value="/board/detail" />
			$.ajax({
				url: "${boardDetailUrl }",
				data: {
					id: ${requestScope.data.id }
				},
				type: "post",
				dataType: "json",
				success: function(data) {
					editor = new toastui.Editor({
						el: document.querySelector("#editor"),
						initialValue: data.context,
						initialEditType: 'wysiwyg'//처음 수정탭이 markdown이 아니라 wysiwyg로 뜸
					});//수정하고 저장할때문제가생길것. 고쳐보라
				}
			});
		}
	</script>
</body>
</html>