<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	window.onload =pageLoad;
	function pageLoad(){
		const notShowPop= getCookieValue();
		if(notShowPop!= "true"){
			window.open("./view/popUp.jsp", "pop", "width=400,height=500,history=no, resizable=no, status=no, scrollbars=yes, menubar=no");
		}
	}
	function getCookieValue(){
		let result="false";
		if(document.cookie != ""){
			cookie=document.cookie.split(";");
			for( let i=0; i<cookie.length; i++){
				element=cookie[i].split("=");
				value=element[0];
				value=value.replace(/^\s*/,'');
				if(value=="notShowPop"){
					result=element[1];
				}
			}
		}
		return result;
	}
	function deleteCookie(){
		document.cookie="notShowPop=" +"false"+ ";path=/; expires=-1";
	}
</script>

 
<body>
	<span id="loginTime"></span>
	<input type="button" value="팝업창뜨게(팝업쿠키삭제)" onClick="deleteCookie()">
	
	현재접속자수: <%= MemberDTO.getTOTAL_USER() %>
	접속아이디: 
	<c:forEach var="data" items="${applicationScope.userList }">
		${data.id }, 
	</c:forEach>
	<c:if test="${sessionScope.login.id.equals('admin')}">
		<h2>관리자 계정으로 로그인 하셨습니다.</h2>
		<a href="./memberList">회원명단</a>
	</c:if>
	<a href="./visit">visit방명록</a>
	
	<a href="./board">게시판</a>
	
	<c:if test="${sessionScope.isLogin == null}">
		<a href="./memberInsert">회원가입</a>
		<a href="./login">로그인</a>
	</c:if> 
	<c:if test="${sessionScope.isLogin == true}">
		<h2> ${sessionScope.login.id } 님 안녕하세요 !</h2>
		<a href= "${pageContext.request.contextPath}/info" }>회원정보</a>
		<a href="./logout">로그아웃</a>

	</c:if> 
	

<script type="text/javascript">
	setInterval("history.go(0)",500000);
</script>
</body>
</html>