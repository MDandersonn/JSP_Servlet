<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<c:url var="recommendUrl" value="/ajax/recommend" />
	<script type="text/javascript">
		function recommend(element, id, type) {
			$.ajax({
				type: "get",
				url: "${recommendUrl }",
				data: {
					id: id,
					type: type
				},
				dataType: "json",
				success: function(data1) {
					if(data1.redirect !== undefined) {
						let message = "추천/비추천은 회원만 할 수 있습니다. 로그인 페이지로 이동합니까?";
						if(confirm(message)) {
							location.href = data1.redirect;
						}
					} else {
						if(data1.type === "success") {
							if(type === "rec") {
								element.innerText = "추천 " + data1.count
								console.log(data1)
								console.log(data1.count)
							} else if(type === "norec") {
								element.innerText = "비추천 " + data1.count
							}
						} else if(data1.type === "error") {
							console.log(data1.msg);
						}
					}
				}
			});
		}
	</script>
	<c:url var="boardUrl" value="/board" />
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div>
		<h1>${requestScope.data.title }</h1>
	</div>
	<div>
		<fmt:formatDate var="createDate" type="both" pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초" value="${requestScope.data.createDate }" />
		<fmt:formatDate var="updateDate" type="both" pattern="yyyy년 MM월 dd일 EEEE a KK시 mm분 ss초" value="${requestScope.data.updateDate }" />
		작성자: ${requestScope.data.writer }<br>
		작성일: ${createDate }<br>
		수정일: ${updateDate }<br>
		조회수: ${requestScope.data.viewCnt }
	</div>
	<div id="viewer"></div><%-- 글내용(Content칼럼)이 여기안에 들어갈것이다.--%>
	<div>
		<ul>
			<c:forEach var="image" items="${requestScope.images }" >
				<c:url var="imagePath" value="${image.path }${image.uuid }" />
				<li><a href="${imagePath }" download="${image.name }">${image.name }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<button type="button" class="btn btn-primary" onclick="recommend(this, ${requestScope.data.id }, 'rec');">
			추천 ${requestScope.data.recCnt }</button>
		<button type="button" class="btn btn-danger" onclick="recommend(this, ${requestScope.data.id }, 'norec');">
			비추천 ${requestScope.data.nRecCnt }</button>
	</div>
	<div>
		<button onclick="location.href='${boardUrl }'">목록</button>
		<c:if test="${sessionScope.login }">
			<c:if test="${sessionScope.user.userId eq requestScope.data.writer }">
				<button onclick="location.href='${boardUrl }/update?id=${requestScope.data.id }'">수정</button>
				<button onclick="location.href='${boardUrl }/delete?id=${requestScope.data.id }'">삭제</button>
			</c:if>
		</c:if>
	</div>
	<script type="text/javascript">
		var viewer1;
		window.onload = function() {
			<c:url var="boardDetailUrl" value="/board/detail" />
			$.ajax({
				url: "${boardDetailUrl }",
				data: {
					id: ${requestScope.data.id }
				},
				type: "post",
				dataType: "json",
				success: function(data) {
					viewer1 = new toastui.Editor.factory({
					//viewer1로 할당하는 의미 딱히 없음. 없애도 작동 잘됨	
						el: document.querySelector("#viewer"),
						viewer: true,//뷰어로 사용하겠다는 의미
						initialValue: data.context//초기값
						//위의 initialValue값을 ajax를 사용하여, 페이지가 로드되었을때 서버에 조회요청을 하고
						//응답 데이터의 context를 찾아서 초기값으로 설정되도록 하였다.
					<%-- 전달받은 리턴값인 data객체의 context속성값을보면 글내용과 함께 이미지파일이 담겨있따. --%>
					<%-- 토스트 에디터ui가  HTML태그라서 가능한것.이대로 DB에 context컬럼에 저장하는것. --%>
					
					<%-- 원래과정: 사용자가 상세페이지를 요청하면 서버에서는 상세페이지에대한 데이터를 조회하고
					조회한 데이터를 JSP로 실행해서 HTML태그안에 삽입. 완성된 HTMl코드가 응답--%>
					
					<%--ajax과정 :사용자가 상세페이지요청을 하면 서버에서 html페이지를 응답한다
							그러면 사용자에서 스크립트를 실행하고 ajax요청 서버는 상세조회하여 데이터 응답 --%>
					});
				}
			});
		}
	</script>
</body>
</html>