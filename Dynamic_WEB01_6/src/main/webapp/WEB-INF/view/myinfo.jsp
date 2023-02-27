<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<script type="text/javascript">
		function previewImage(element) {
			console.log(element);
			console.log(element.files);
			console.log(URL.createObjectURL(element.files[0]));
			
			var preview = document.getElementById("preview");
			preview.src = URL.createObjectURL(element.files[0]);
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<h1>개인 정보 수정</h1>
	<c:url var="myinfoUrl" value="/myinfo" />
	<form action="${myinfoUrl }/uploadImage" method="post" enctype="multipart/form-data">
		<div>
			<c:url var="imgUrl" value="${sessionScope.user.pImg }" />
			<img id="preview" src="${imgUrl }" alt="여기에 이미지가 표시됩니다." width="150px" height="150px">
		</div>
		<label>이미지 선택</label>
		<input type="file" name="imageFile" onchange="previewImage(this);" accept="image/png, image/jpeg, image/jfif">
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<form action="${myinfoUrl }" method="post">
		<div>
			<label>아이디</label>
			<input type="text" value="${sessionScope.user.userId }" disabled>
		</div>
		<div>
			<label>현재 패스워드</label>
			<input type="password" name="password">
		</div>
		<div>
			<label>변경 할 패스워드</label>
			<input type="password" name="changePass">
		</div>
		<div>
			<label>이메일 주소</label>
			<input type="email" name="email" value="${sessionScope.user.email }">
		</div>
		<div>
			<button type="submit">변경하기</button>
		</div>
	</form>
</body>
</html>