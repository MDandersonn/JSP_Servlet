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
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>

</head>
<style>
	
</style>
<body>
	<a href="../">메인으로</a>
	
	<h1>게시판</h1>
	<form action ="${pageContext.request.contextPath }/board/insert" method="post" enctype="multipart/form-data">
		제목:<input type="text" name="title"><br>
		내용:<textarea name="content"></textarea><br>
		이미지: <input type="file" name="img" onchange="previewImage(this);"  accept="image/png, image/jpeg, image/jfif"><br>
		<img id='img' src="${pageContext.request.contextPath }/static/img/profile/default.png" width="150px" height="150px"></img>
		<br>
		<br>
		<br>
		여러장 올리기: 이미지: <input type="file" name="imgMulti" onchange="imageValid(this);"  accept="image/*" multiple><br><br>
		<input type="submit" value="작성">
	
	</form>
	<script>
		function previewImage(element){
			$("#img").attr("src",URL.createObjectURL(element.files[0]));
		}
		function imageValid(element){
			if(element.files.length >3){
				alert("이미지는 3개까지만가능");
				element.value="";
			}
			
		})
	</script>
	


</body>
</html>