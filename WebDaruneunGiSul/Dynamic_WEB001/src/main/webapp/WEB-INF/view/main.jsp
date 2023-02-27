<!-- 지시자 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%!
	//선언부
	public Random rand= new Random();
	public int getNumber(){
		return rand.nextInt(10);
	}
	//주석되네  HTML코드랑 자바코드랑 섞여서들어가는데 이걸 JSP(자바서버페이지)라고한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url var="staticUrl" value="/static" />
<link type="text/css" rel="stylesheet" href="${staticUrl}/bs5/css/bootstrap.min.css">
<script type="text/javascript" src="${staticUrl}/bs5/js/bootstrap.bundle.min.js"></script> 
</head>
<body>
	<h2>JSP파일(main.jsp)이 실행되었습니다.dd</h2>
	<form action="./main">
	<!--메서드 안쓰면 기본이 get방식  -->
	<!--다시 또 메인 호출  -->
		<input type="number" name="num">
		<!--number라고해서 이게 타입이 넘버가아니다 숫자양식을 입력하는거지 문자열이다.-->
		<!--서버에데이터를 전송해서 서버에서받아서 처리하게 만들게  -->
		
		<button type="submit">전송</button>
	</form>
	<h2><%=(Integer)request.getAttribute("number") %> 숫자타입</h2>
	<h2><%=request.getParameter("num") %> 문자타입</h2>
	<!-- jsp에서도 리퀘스트객체를쓸수있당 왜냐면 서블릿에서 리퀘스트겍체를 넘겨줬으니까. -->
	
	
	<ul>
	<!--  스크립트릿-->
	<% for(int i=getNumber();i>=0;i--){ %>
		<li><%=i %></li><!--  표현식-->
	<%}%>
	</ul>
	
	
	
	<h3>EL-expression language</h3>
	<!--EL 오류원래못잡는다 무시해도된다.  -->
	<%="값" %> -> ${"값"}
	<br>
	${10+20 }<br>
	${10 div 3} <br>
	
	<!--  10%3-->
	${10 mod 3} <br>
	${10 eq 10} <br>
	${11 ne 10} <br>
	${20 gt 10} <br>
	${10 ge 10} <br>
	${5 lt 10} <br>
	${true and true} <br>
	${true or true} <br>
	${not true} <br>
	
	<!--  null또는 빈문자열일때-->
	${empty x} <br>
	<!--  3항연산-->
	${empty x ? "없음":"있음" }<br>
	<%-- ${"문자열"+ "문자열" }<br> 숫자랑 문자열합한것도 에러남  그냥 붙여서 아래처럼쓰면됨--%>
	${"문자열"}${"문자열"}
	
	<h4>EL 로 request.getAttribute()사용</h4>
	${requestScope.number} <br>
	
	<h4> EL로 request.getParameter()사용 </h4>
	${param.num } <br>
	
	
	
	<h3>JSTL-core</h3>
	<h4>변수 설정</h4>
	<c:set var="변수명" value="값"/>
	<c:set var="name" value="10"/>
	<!-- 출력문 -->
	${name }<br>
	
	<h4>변수 삭제</h4>
	<c:remove var="name" />
	${empty name ? "없음" : "있음" } <br>
	
	<h4>조건문</h4>
	<%-- <c:if test="조건식"> --%>
	<c:set var="num" value="10" />
	
	<c:if test="${num eq 10 }">
		num에 저장된 값은 10입니다.
	</c:if>
	<br>
	
	<c:if test="${true}">
		조건식이 참일 때 출력할 내용
	</c:if>
	<br>
	
	<c:choose>
	<%--	<c:when test="조건식1">  --%>
		<c:when test="${true}">
			조건식이 참일때 출력
		
		</c:when>
		
		<c:when test="${false}">
			첫번째 조건식이 거짓이고 두번째 조건식이 참일때 출력
		</c:when>
		
		<c:otherwise>
			<!--else문 생략가능  -->
			모든 조건식이 거짓일때 출력
		</c:otherwise>
	</c:choose>
	
	
	
	
	<h4>반복문</h4>
	<%--<c:forEach var ="변수명" begin="시작값" end="종료값">  --%>
	<c:forEach var ="i" begin="0" end="5">
		${i },
	
	</c:forEach>
	<br>
	<c:forEach var ="i" begin="0" end="5" step="2">
		${i },
	
	</c:forEach>
	<br>
	<!--  배열생성-->
	<!--  안에 내용물이 있을때는 /> 로 끝내지않는다.-->
	<c:set var="arr">
		a,b,c,d,e,f
	</c:set>
	<br>
	<!--  items="배열또는 리스트컬렉션" -->
	

	<c:forEach var="i" items="${arr }">
		${i } /
	</c:forEach>
	<br>
	
		<!--0번부터시작하는건 인덱스 1번부터시작하는건 count
		첫번째반복이냐 마지막반복이냐 이거를 확인하는 기능도있다.	
	  -->
	<c:forEach var="i" items="${arr }" varStatus="loop">
		값: ${i } | index: ${loop.index } | count: ${loop.count } | first: ${loop.first }  |  last:${loop.last }<br>
	</c:forEach>
	
	<!-- var="변수명" items="문자열" delims="구분문자" -->
	<c:forTokens  var ="s" items="010-1234-5678" delims="-" >
		${s }<br>
	</c:forTokens>
	<br>
	
	<h4>URL주소생성</h4>
	
	<!-- 
	알아서 url주소앞에 contextpath를 앞에 붙여줌
	var="변수명" value="URL주소" 
	value앞에 contextpath가 자동으로 붙는다.
	기본경로는  설정 프로퍼티스-> web project settings가면 변경가능 
	-->
	<c:url var="myUrl" value="/home" />
	
	${myUrl }<br>
	
	<!-- 파라미터 작성하기. -->
	<c:url var="myUrl" value= "/home">
		<c:param name="name" value="value" />
		<c:param name="key" value="context" />
	</c:url>
	${myUrl }<br>
	
	
	
	
	
	<h3>JSTL-Function</h3>
	<!--문자열관련된 기능제공 EL에다가 적용해서사용  -->
	<h4>contains(전체문자열, 일부문자열)</h4>
	
	<!-- true/false로 반환 포함되어있으면 true (대소문자구분함)  -->
	${fn:contains("Hello JSTL Tag Library", "JSTL")}
	
	<br>
	${fn:containsIgnoreCase("Hello JSTL Tag Library", "jstl")}
	<br>
	
	<h4>replace(전체문자열,변경전문자열,변경후문자열)</h4>
	${fn:replace("Hello JSTL Tag Library","Tag","태그") }
	
	
	<h4>split(전체문자열,분리구분문자)<h4>
	<!--배열주소반환  -->
	${fn:split("Hello JSTL Tag Library", " ") }
	
	<c:forEach var="s" items="${fn:split('Hello JSTL Tag Library',' ') }">
		${s }<br>
	</c:forEach>
	
	<h4>toUpperCase(전체문자열)</h4>
	${fn:toUpperCase("    Hello JSTL Tag Library") }
	<h4>toLowerCase(전체문자열)</h4>
	${fn:toLowerCase("     Hello JSTL Tag Library") }
	
	<h4>trim(전체문자열)</h4>
	${fn:trim("    Hello JSTL Tag Library     ") }
	
	<h4>length(전체문자열)</h4>
	${fn:length("Hello JSTL Tag Library") }
	
	<h4>substring(전체문자열,시작위치,끝위치)</h4>
	${fn:substring("Hello JSTL Tag Library",6,10 ) }
	
	
	<h3>JSTL-Formatting</h3>
	<h4>숫자 포멧</h4>
	
	<c:set var="num1" value="123456789" />
	<c:set var="num2" value="12345.6789" />
	<c:set var="num3" value="0.1234" />
	<fmt:formatNumber value="${num1 }" type="number" /><br>
	<fmt:formatNumber value="${num2 }" type="number" /><br>
	
	<fmt:formatNumber value="${num1 }" type="number" groupingUsed="false"/><br>
	<fmt:formatNumber value="${num2 }" type="number" groupingUsed="false"/><br>
	
	
	<fmt:setLocale value="en_US" />
	<fmt:formatNumber value="${num1 }" type="currency"/><br>
	
	<fmt:setLocale value="ko_KR" />
	<fmt:formatNumber value="${num2 }" type="currency"/><br>
	<fmt:formatNumber value="${num2 }" type="currency" currencySymbol="원"/><br>
	<fmt:formatNumber value="${num2 }" type="currency" pattern="###,###,###원"/><br>
	
	
	<fmt:formatNumber value="${num3 }" type="percent"/><br>
	<fmt:formatNumber value="${num3 }" type="percent" maxFractionDigits="2"/><br>
	
	<h4>날짜 포멧</h4>
	<c:set var="now" value="<%=new java.util.Date() %>" />
	
	<%-- <fmt:setLocale value ="en_US" />--%>
	<%--기본값이 medium --%>
	
	<fmt:formatDate value="${now }" type = "date" /><br>
	<fmt:formatDate value="${now }" type = "date" dateStyle="short"/><br>
	<fmt:formatDate value="${now }" type = "date" dateStyle="long"/><br>
	<fmt:formatDate value="${now }" type = "date" dateStyle="medium"/><br>
	
	
<%-- <fmt:setLocale value ="en_US" />--%>
	<fmt:formatDate value="${now }" type = "time" timeStyle="short"/><br>
	<fmt:formatDate value="${now }" type = "time" timeStyle="long"/><br>
	<fmt:formatDate value="${now }" type = "time" timeStyle="medium"/><br>
	
	
	<fmt:formatDate value="${now }" type = "both" /><br>
	<fmt:formatDate value="${now }" type = "both" dateStyle="short" timeStyle="long"/><br>
	<fmt:formatDate value="${now }" type = "both" dateStyle="long" timeStyle="medium"/><br>
	<fmt:formatDate value="${now }" type = "both" pattern="yyyy-MM-dd a hh:mm:ss E요일 "/><br>
	
	<fmt:parseDate var="pDate" value="2023-01-17" pattern="yyyy-MM-dd" />
	<fmt:formatDate value="${pDate }" type="date" />
	<br>
	
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