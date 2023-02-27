<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 여기는 web.xml에서 에러페이지로 맵핑해준 페이지가아니라 이미지업로드컨트롤러에서 에러가났을때  여기로 포워딩해주는것.--%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 업로드</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
</head>
<body>
	<c:choose>
		<c:when test="${errorType eq 'fileExtError' }">
			<h1>잘못된 파일 포멧 입니다.</h1>
			<p><%--web.xml에 <context-param>맵핑해준것을 이렇게 받으면된다. --%>
				허용된 파일 포멧은 ${initParam.permitFileExt } 입니다.
			</p>
		</c:when>
		<c:when test="${errorType eq 'fileTypeError' }">
			<h1>잘못된 파일 종류 입니다.</h1>
			<p>
				허용된 파일 종류는 ${initParam.permitFileType } 입니다.
			</p>
		</c:when>
		<c:when test="${errorType eq 'fileSizeError' }">
			<h1>파일의 크기가 너무 큽니다.</h1>
			<p>
				허용된 파일 크기는 ${initParam.maxFileSize } 바이트 입니다.
			</p>
		</c:when>
	</c:choose>
	<h1></h1>
</body>
</html>