<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가 페이지</title>
<%@ include file="/WEB-INF/view/module/bootstrap.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		function imageValid(element) {
			if(element.files.length > 3) {
				alert('이미지는 3개 까지만 선택할 수 있습니다.');
				element.value = "";
			}
		}
		function sendForm(form) {
			// Toast UI Editor 에 작성된 글 가져와서 전송할 폼(textarea)에 넣기.
			form.context.innerText = editor.getHTML();//에디터에 입력한내용 가져오라는거
			form.submit();//여기서 submit하는거고
			return false;//이게 true면 또 submit작업이 일어나게되므로 false로한거.
			//submit()으로전송하고 false로 전송더안하게.
		}
	</script>
	<div>
		<%@ include file="/WEB-INF/view/module/topnav.jsp" %>
	</div>
	<div>
		<h1>글 작성</h1>
	</div>
	<div>
		<c:url var="boardAddUrl" value="/board/add" />
		<form action="${boardAddUrl }" method="post" onsubmit="return sendForm(this);">
		<%--enctype도 빼버리고 --%>
			<div>
				<label>제목</label>
				<input type="text" name="title">
			</div>
			<div>
				<label>내용</label>
				<div id="editor"></div>
				<textarea style="display: none;" name="context"></textarea>
			</div>
			<!-- div>
				<label>이미지 업로드</label>
				<input type="file" onchange="imageValid(this);" name="imageUpload" accept="image/*"  multiple>
			</div -->
			<c:if test="${sessionScope.role.type eq 'ADMIN' }">
				<div>
					<label>공지사항</label>
					<input type="checkbox" name="notice" value="yes">
				</div>
			</c:if>
			<div>
				<button type="submit">저장</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		const editor = new toastui.Editor({
			el: document.querySelector('#editor'),
			height: "250px",
			hooks: {
				"addImageBlobHook": function(blob, callback) {
					// blob: 사용자가 선택한 이미지 파일
					// callback: 파일이 업로드 된 후 에디터에 표시할 이미지 주소를 전달하기 위한 콜백함수
					let formData = new FormData();
					formData.append("imageUpload", blob);
									//키           , 밸류
									//이미지같은 파일업로드할때 formdata만들어서 키-밸류 쌍으로 데이터 추가하고
									//ajax로 보낼때 씀.
					formData.append("location", "board");
					//이미지를 업로드하면 ajax를 통해서 이미지만 따로 서버에 업로드요청. 서버에서는 업로드 이미지를 받아서 저장하고
					//데베에도 저장하고 아니면 임시적으로 어디에저장했다고 기록을 남겨놓고 리턴을시킨다.
					//이미지경로가 리턴되는데 이를 콜백함수에 넣어서 토스트에게 알려주면 토스트가 알아서 작성한 줄 중간에 경로를 넣어줌
					<c:url var="imageUploadUrl" value="/ajax/imageUpload" />
					$.ajax({
						url: "${imageUploadUrl }",
						type: "post",
						data: formData,
						dataType: "json",
						enctype: "multipart/form-data",//ajax로 멀티파트폼데이터보낼때는 아래와같은 추가속성이 필요하다 
						processData: false,
						contentType: false,
						success: function(data) {
							console.log(data)
							callback(data.imageUrl);
						}
					});
				}
			}
		})
	</script>
</body>
</html>