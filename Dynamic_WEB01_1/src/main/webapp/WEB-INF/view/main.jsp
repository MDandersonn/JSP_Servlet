<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%!
    public Random rand = new Random();

	public int getNumber() {
		return rand.nextInt(10);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl }/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl }/bs5/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h2>JSP 파일이 실행되었습니다.</h2>
	<form action="./main">
		<input type="number" name="num">
		<button type="submit">전송</button>
	</form>
	<h2><%=(Integer)request.getAttribute("number") %></h2>
	<ul>
	<% for(int i = getNumber(); i >= 0; i--) { %>
	       <li><%= i %></li>
	<% } %>
	</ul>
	<%="값" %> -> ${"값" }<br>
	
	<h3>EL - Expression Language</h3>
	${10 + 20 }<br>
	${10 div 3 }<br>
	${10 mod 3 }<br>
	${10 eq 10 }<br>
	${10 ne 10 }<br>
	${10 gt 10 }<br>
	${10 ge 10 }<br>
	${10 lt 10 }<br>
	${10 le 10 }<br>
	${true and true }<br>
	${true or true }<br>
	${not true }<br>
	${empty x }<br>
	${empty x ? "없음" : "있음" }<br>
	
	${"문자열" }${"문자열" }<br>
	${10 }${"문자열" }<br>
	
	<h4>EL 로 request.getAttribute() 사용</h4>
	${requestScope.number }<br>
	
	<h4>EL 로 request.getParameter() 사용</h4>
	${param.num }<br>
	
	<h3>JSTL - Core</h3>
	<h4>변수 설정</h4>
	<c:set var="name" value="10" />
	${name }<br>
	
	<h4>변수 삭제</h4>
	<c:remove var="name" />
	${empty name ? "없음" : "있음" }<br>
	
	<h4>조건문</h4>
	<c:set var="num" value="10" />
	<c:if test="${num eq 10 }">
		num에 저장된 값은 10 입니다.
	</c:if>
	<br>
	<c:choose>
		<c:when test="${num lt 5 }">
			num에 저장된 값은 5보다 작습니다.
		</c:when>
		<c:when test="${num lt 10 }">
			num에 저장된 값은 5 이상이고 10 미만입니다.
		</c:when>
		<c:otherwise>
			num에 저장된 값은 10 이상 입니다.
		</c:otherwise>
	</c:choose>
	<br>
	
	<h4>반복문</h4>
	<c:forEach var="i" begin="0" end="5" step="2">
		${i } ,
	</c:forEach>
	<br>
	
	<c:set var="arr">
		a, b, c, d, e, f
	</c:set>
	<c:forEach var="i" items="${arr }" varStatus="loop">
		값: ${i } | index: ${loop.index } | count: ${loop.count } | first: ${loop.first } | last: ${loop.last }<br>
	</c:forEach>
	<br>
	
	<c:forTokens var="s" items="010-1234-5678" delims="-">
		${s }<br>
	</c:forTokens>
	<br>
	
	<h4>URL 주소 생성</h4>
	<c:url var="myUrl" value="/home" />
	${myUrl }<br>
	
	<c:url var="myUrl" value="/home">
		<c:param name="name" value="value" />
		<c:param name="key" value="context" />
	</c:url>
	${myUrl }<br>
	
	<h3>JSTL - Function</h3>
	
	<h4>contains(전체문자열, 일부문자열)</h4>
	${fn:contains("Hello JSTL Tag Library", "jstl") }
	
	<h4>containsIgnoreCase(전체문자열, 일부문자열)</h4>
	${fn:containsIgnoreCase("Hello JSTL Tag Library", "jstl") }
	
	<h4>replace(전체문자열, 변경전문자열, 변경후문자열)</h4>
	${fn:replace("Hello JSTL Tag Libray", "Tag", "태그") }
	
	<h4>split(전체문자열, 분리구분문자)</h4>
	<c:forEach var="s" items="${fn:split('Hello JSTL Tag Libray', ' ') }">
		${s }<br>
	</c:forEach>
	
	<h4>toUpperCase(전체문자열)</h4>
	${fn:toUpperCase("Hello JSTL Tag Libray") }
	
	<h4>toLowerCase(전체문자열)</h4>
	${fn:toLowerCase("Hello JSTL Tag Libray") }
	
	<h4>trim(전체문자열)</h4>
	${fn:trim("     Hello JSTL Tag Libray     ") }
	
	<h4>length(전체문자열)</h4>
	${fn:length("Hello JSTL Tag Libray") }
	
	<h4>substring(전체문자열, 시작위치, 끝위치)</h4>
	${fn:substring("Hello JSTL Tag Libray", 6, 10) }
	
	
	
	<h3>JSTL - Formatting</h3>
	<h4>숫자 포멧</h4>
	<c:set var="num1" value="123456789" />
	<c:set var="num2" value="12345.6789" />
	<c:set var="num3" value="0.1234" />
	
	<fmt:formatNumber value="${num1 }" type="number" /><br>
	<fmt:formatNumber value="${num2 }" type="number" /><br>
	
	<fmt:formatNumber value="${num1 }" type="number" groupingUsed="false" /><br>
	<fmt:formatNumber value="${num2 }" type="number" groupingUsed="false" /><br>
	
	<fmt:setLocale value="en_US" />
	<fmt:formatNumber value="${num1 }" type="currency" /><br>
	
	<fmt:setLocale value="ko_KR" />
	<fmt:formatNumber value="${num2 }" type="currency" currencySymbol="원" /><br>
	<fmt:formatNumber value="${num2 + 1234567 }" type="currency" pattern="###,###.###원" /><br>
	
	<fmt:formatNumber value="${num3 }" type="percent" /><br>
	<fmt:formatNumber value="${num3 }" type="percent" maxFractionDigits="2" /><br>
	
	<h4>날짜 포멧</h4>
	<c:set var="now" value="<%=new java.util.Date() %>" />
	
	<%--fmt:setLocale value="en_US" /--%>
	<fmt:formatDate value="${now }" type="date" /><br>
	<fmt:formatDate value="${now }" type="date" dateStyle="short" /><br>
	<fmt:formatDate value="${now }" type="date" dateStyle="long" /><br>
	
	<%--fmt:setLocale value="en_US" /--%>
	<fmt:formatDate value="${now }" type="time" /><br>
	<fmt:formatDate value="${now }" type="time" timeStyle="short" /><br>
	<fmt:formatDate value="${now }" type="time" timeStyle="long" /><br>
	
	<fmt:formatDate value="${now }" type="both" /><br>
	<fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short" /><br>
	<fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long" /><br>
	
	<fmt:formatDate value="${now }" type="both" pattern="yyyy-MM-dd a hh:mm:ss E요일" /><br>
	
	<fmt:parseDate var="pDate" value="2023-01-17" pattern="yyyy-MM-dd" />
	<fmt:formatDate value="${pDate }" type="date" />
	
	
	<hr>
	<fmt:setTimeZone value="GMT-1" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="medium" /><br>
	
	<fmt:setTimeZone value="GMT+1" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="medium" /><br>
	
	<fmt:setTimeZone value="GMT" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="medium" /><br>
	
	<fmt:setTimeZone value="GMT+9" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="medium" /><br>
</body>
</html>