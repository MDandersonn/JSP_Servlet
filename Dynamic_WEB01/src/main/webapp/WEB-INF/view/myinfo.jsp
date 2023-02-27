<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보수정</title>
<c:url var="myinfoUrl" value="/myinfo" />
<form action="${myinfoUrl }" method="post">
	<div>
		<label>아이디</label>
		<input type="text" value="${sessionScope.user.userId}" disabled>
	</div>
	<c:if test="${not empty requestScope.error }">
			<span style="color:red;">${requestScope.error }</span>
	</c:if>
	<div>
		<label>현재 패스워드</label>
		<input type="password" name="password">
	</div>
	<div>
		<label>변경할 패스워드</label>
		<input type="password" name="changePass">
	</div>
	<div>
		<label>이메일 주소</label>
		<input type="email" name="email" value="${sessionScope.user.email}">
	</div>
	<div>
		<button type="submit">변경하기</button>
	</div>
</form>

</head>
<body>

</body>
</html>