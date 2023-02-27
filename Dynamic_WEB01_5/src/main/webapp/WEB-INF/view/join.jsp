<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
</head>
<body>
	<c:url var="userIdCheck" value="/ajax/userIdCheck" />
	<script type="text/javascript">
		valid = false;//전역변수 설정
		function usernameCheck(element) {
			if(element.nextElementSibling === null) {
				var s = document.createElement("span");
				element.after(s);
			} else {
				var s = element.nextElementSibling;
			}
			$.ajax({
				//type: get또는 post로 서버에 요청을 구분하여 보내기위해 사용
				//url: 서버 URL주소(컨트롤러에 맵핑된 주소)   "/컨텍스트명/ajax/userIdCheck"
				//data: 서버에 전송할 데이터. 키-값형식으로 구성
				//dataType: 서버에서 응답하는 데이터 종류(json,xml,html,plan)
				//success: 성공적으로 응답이 왔을 때 동작하는 함수
				//error: 응답이 왔지만 데이터처리에대한문제가발생했을때,(없어도됨)
				//complete :응답이 success/error이든 상관없이 동작이 필요할떄 사용하는 함수(없어도 됨)
				//서버에서 JSP,JSTL을 실행해서 나온결과를 자바스크립트 코드 담아서 클라이언트에 전송, 클라이언트에서 자바스크립트실행
				
				type: "get", 
				url: "${userIdCheck }",
				data: { 
					userId: element.value
					},
				dataType: "json",
				success: function(data1) {
					console.log("응답이 성공적으로 왔습니다.");
					console.log("data:"+data1);
					console.log(data1);//function의 매개변수 data1은 응답객체임
					if(data1.msg === "OK") {
						s.style = "color: green;";
						s.innerText = "회원 가입이 가능한 아이디 입니다.";
						valid = true;
					} else if(data1.msg === "Fail") {
						s.style = "color: red;";
						s.innerText = "회원 가입이 불가능한 아이디 입니다.";
						valid = false;
					} else {
						console.log("알 수 없는 값 입니다.");
						valid = false;
					}
				},
				error: function(data1) {
					// 응답이 왔지만 데이터 처리에 대한 문제가 발생 했을 때 동작하는 함수
					console.log("error: 응답이 왔지만 문제가 발생하였습니다.");
					console.log(data1);
				},
				complete: function(data1) {
					// 응답이 success / error 이든 상관없이 동작이 필요할 때 사용하는 함수
					console.log("complete: 서버로 부터 응답이 왔습니다. 성공하든실패하든 이건 작동합니다..");
					console.log(data1);
				}
			});
		}
		
		function validCheck() {
			if(valid) {
				return true;//전송이 이루어짐
			} else {
				alert("아이디 확인 부터 확인하세요.");
				return false; // 전송이 안이루어짐
			}
		}
	</script>
	<h1>회원가입</h1>
	<c:url var="joinUrl" value="/join" />
	<form action="${joinUrl }" method="post" onsubmit="return validCheck();">
	<%-- onsubmit= "return 함수;" return true면 전송 false면 전송안함. --%>
		<div>
			<label>아이디</label>
			<input type="text" name="userId" onchange="usernameCheck(this);">
		</div>
		<div>
			<label>패스워드</label>
			<input type="password" name="password">
			${error }
		</div>
		<div>
			<label>이메일</label>
			<input type="email" name="email" value="${param.email }">
		</div>
		<button type="submit">가입</button>
	</form>
</body>
</html>