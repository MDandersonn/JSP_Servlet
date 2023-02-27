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
			//this.files 하면 fileList객체가나오고 그안에 내가선택한 이미지 객체가 들어가있다
			//URL.createObjectURL(파일객체) : 파일에대한 URL경로를 만듦
			var preview = document.getElementById("preview");
			preview.src = URL.createObjectURL(element.files[0]);
			//기본이미지가 선택한 이미지파일로 바뀜
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<h1>개인 정보 수정</h1>
	<c:url var="myinfoUrl" value="/myinfo" />
												<%--	enctype="multipart/form-data": 파일업로드를 하려면 꼭 써야함
		파일은 바이트데이터이고 용량도 커서 여러번 쪼개서 보낸다 문자열이아닌 바이트데이터를 보내야되어서 파일을 보내줄수있게 처리를바꿔라 라는 의미
		일반 파일을 사용자입력데이터랑 같이보낼때도 써줘야함  즉 파일을 보내는거면 무조건 써야함--%>
	<form action="${myinfoUrl }/uploadImage" method="post" enctype="multipart/form-data">
	<%-- "/myinfo/uploadImage" --> 이미지업로드컨트롤러   --%>
		${sessionScope.user.pImg }
		${sessionScope.user}
		<div>
			<c:url var="imgUrl" value="${sessionScope.user.pImg }" /><%--이건 기본이미지로 설정된거 보여줌 --%>
			<img id="preview" src="${imgUrl }" alt="여기에 이미지가 표시됩니다." width="150px" height="150px">
		</div>
		<label>이미지 선택</label>
		<%--  accept="image/*" 종류: image  상세확장자: *(모든것) 이라는의미 
		  accept="image/png, image/jpeg, image/jfif" :파일선택할때 해당파일만 보여줌 --%>
		  
		<input type="file" name="imageFile" onchange="previewImage(this);" accept="image/png, image/jpeg, image/jfif">
		<%-- input태그안에 multiple이라고 쓰면 여러 파일을 업로드 가능하다.--%>
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