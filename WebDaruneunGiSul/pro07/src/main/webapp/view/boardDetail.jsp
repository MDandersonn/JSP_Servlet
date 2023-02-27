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

<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
</head>
<style>
	
</style>
<body>
	<a href="../">메인으로.</a>
	<div>
		글번호:${data.articleNO } <br>
		작성자 아이디: ${data.id }<br>
		<h6>제목: ${data.title }</h6><br>
		내용: ${ data.content}<br>
		이미지: ${data.imageFileName }<br>
		<img src="${pageContext.request.contextPath}/${data.imageFileName }"></img><br>
		
		등록일자: ${data.writedate }<br>
		<%--ajax쓸땐 제이쿼리 스크립트가져오는거 잊지말고 --%>
		<button id ='up' class="btn btn-primary" onclick="up(this,${data.articleNO});">추천 ${data.recUP }</button>
		<button id ='down' class="btn btn-danger" onclick="down(this,${data.articleNO});">비추천 ${data.recDOWN }</button>
 		
	</div>
	
	<div>
		<ul>
			<c:forEach var="image" items="${requestScope.images }" >
				<c:url var="imagePath" value="${image.path }${image.uuid }" />
				<li><a href="${imagePath }" download="${image.name }">${image.name }</a></li>
			</c:forEach>
		</ul>
	</div>
	<script>
	
	function up(element,articleNO1){
		$.ajax({
			type : "post",
			url: "${pageContext.request.contextPath}/ajax/recup",
			data :{
				articleNO: articleNO1
			},
			dataType : "json",//대소문자잘못되면  json객체로안와서 제대로못읽음
			success: function(data1){
				console.log("작동");
				console.log(data1);
				console.log(data1.dataUP);
				console.log(data1.dataDOWN);
				console.log(data1[1]);
				//element.innerText="추천"  + data1.dataUP;
				$("#up").text("추천 "+data1.dataUP);
				$("#down").text("비추천 "+data1.dataDOWN);
			}
		})
	}
	function down(element,articleNO1){
		$.ajax({
			type : "post",
			url: "${pageContext.request.contextPath}/ajax/recdown",
			data :{
				articleNO: articleNO1
			},
			dataType : "json",//대소문자잘못되면  json객체로안와서 제대로못읽음
			success: function(data1){
				console.log("작동");
				console.log(data1);
				console.log(data1.dataUP);
				console.log(data1.dataDOWN);
				console.log(data1[1]);
				//element.innerText="추천"  + data1.dataUP;
				$("#up").text("추천 "+data1.dataUP);
				$("#down").text("비추천 "+data1.dataDOWN);
			}
		})
	}
		
	</script>


</body>
</html>