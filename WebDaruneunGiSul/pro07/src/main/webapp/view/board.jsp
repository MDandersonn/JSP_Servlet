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

</head>
<style>
	
</style>
<body>
	<a href="./">메인으로</a>
	
	<h1>게시판</h1>
	<form action ="./board" method="get">
		<select name="c" onchange="submit();">
			<option value="5" ${cnt eq 5 ? "selected":"" }>5개</option>
			<option value="10" ${cnt eq 10 ? "selected":"" }>10개</option>
			<option value="20" ${cnt eq 20 ? "selected":"" }>20개</option>
			<option value="25" ${cnt eq 25 ? "selected":"" }>25개</option>
		</select>
	</form>
	
	<br></br>
	<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/board/insert'">글작성</button>
	
	<table border="1" style="border-collapse:collapse;">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
			<td>수정</td>
			<td>삭제</td>
			
		</tr>
		<c:forEach var="data" items="${requestScope.data }">
			<tr>
				<td>${data.articleNO }</td>
				<td>${data.id }</td>
				<td><a href="./board/detail?articleNO=${data.articleNO }">${data.title }</a></td>
				<td>${data.writedate }</td>
				
				<td><button  onclick="location.href='./boardUpdate?id=${data.articleNO}'">수정</button></td>
				<td><button  onclick="location.href='./boardDelete?id=${data.articleNO}'" >삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	<ul class="pagination">
		<li class="page-item ${p <= pageLimit ? 'disabled' :'' }"><a class="page-link" href="./board?p=${prev }">Previous</a></li>
		<li class="page-item ${p eq 1 ? 'disabled' :'' }">
	      <a class="page-link" href="./board?p=${p-1 }" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
		<c:forEach var="i" begin="${bigPage - pageLimit +1 }" end ="${lastPage }">
				<li class="page-item ${ p eq i ? 'active':''}"><a class="page-link" href="./board?p=${i }">${i }</a></li>
		</c:forEach>
		<li class="page-item ${p eq totalPageNum ? 'disabled' :'' }">
	      <a class="page-link" href="./board?p=${p +1}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    <li class="page-item ${bigPage >= totalPageNum ? 'disabled' :'' }"><a class="page-link" href="./board?p=${next }">Next</a></li>
	</ul>
	
	



</body>
</html>