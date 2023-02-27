<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
</head>
<style>
</style>
<body>
	<a href="./">메인으로</a>
	<a href="./memberList">회원명단보기</a><br>
	<h1>회원가입</h1>
	<form action="./memberInsert" method="post">
		아이디:<input id="id" type="text" name="id"/><button id="but" type="button" onClick="checkId(this);"> ID중복확인</button>
		<span id="output"></span><br>
		비밀번호:<input type="text" name="pwd"/><br>
		이름:<input type="text" name="name"/><br>
		email:<input type="text" name="email"/><br>
		<input type="submit" value="가입하기"><input type="reset" value="다시입력">
		
	</form>
	
	<%--  =가아닌 : 로쓰기,  콤마 제대로잘달기 --%>
	<script>
		function checkId(){
			const id1= $("#id").val();
			console.log(id1);
			$.ajax({
				type:"post",
				dataType :  "json",
				url:"./ajax/userIdCheck",
				data:{
					id: id1 },
				success: function(data1){
					if(data1.msg=='usable'){
						console.log(data1);
						$("#output").html("<div style='color:green;'> 사용할수 있는 id입니다.</div>");
						$("#but").prop("disabled",true);
					}else{
						$("#output").html("<div style='color:red;'> 사용할수 없는 id입니다.</div>");
					}
				},
				
				error: function(data1){
					$("#output").text("에러.");
				},
				
				complete:function(data1){
					console.log("항상작동");
				}
			
				
			})
		}
	</script>


</body>
</html>