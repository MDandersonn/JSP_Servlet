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
</head>
<script type="text/javascript">
function setPopUpStart(obj){
	if(obj.checked==true){
		let expireDate= new Date();
		let days =1;
		expireDate.setDate(expireDate.getDate()+days);
		document.cookie = "notShowPop="+"true" + ";path=/; expires="+ expireDate.toGMTString();
		window.close();
	}
	
}
</script>

<body>
팝업창입니다.
<form>
	<input type="checkbox" onClick="setPopUpStart(this)">오늘 더이상 팝업창 띄우지 않기.
</form>

</body>
</html>