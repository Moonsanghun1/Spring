<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글보기</title>
<!-- 1. 날짜 처리 함수 선언  -->
<script src="/resources/js/dateTime.js"></script>

<style type="text/css">
</style>
<!-- 2. 필요한 전역변수를 직접 입력 -->
<script type="text/javascript">
	// 보고있는 일반 게시판의 글번호를 전역 변수로 지정해보자.
	let no = ${vo.no};
	let replyPage = 1;
	console.log("전역 변수 no: " + no );
	// 강제 로그인- 내가 작성한 댓글에 대해서만 수정과 삭제가 가능하도록 만들기 위해서
	id = "test";
</script>
<!-- 3. 댓글 객체(replyService)를 선언 : Ajax 처리 포함  -->
<script src="/resources/js/reply.js"></script>
<!-- 4. reply 호출 처리 함수 선언 + 이벤트 처리 -->
<script type="text/javascript" src="/resources/js/replyProcess.js"></script>
<script type="text/javascript">
$(function(){
	
	// 이벤트 처리
	// 글보기 이동 처리
	$(".dataRow").click(function(){
		let no = $(this).data("no");
		// alert(no);
		location = "view.do?no=" + no + "&inc=1";
	});
	$("#updateBtn").click(function(){
		let no = $(this).data("no");
		// alert(no);
		location = "updateForm.do?no=" + no + "&inc=0";
	});
	
	$("#delete").click(function(){
		$("#pw").val("");
	});
	
	
	
});


</script>

</head>
<body>
<div class="container">
	<div class="card">
	  <div class="card-header"><h2>일반 게시판 글보기</h2></div>
	  <div class="card-body">
			<div class="card dataRow" data-no="${vo.no }">
			  <div class="card-header">
			  	<span class="float-right">조회수 : ${vo.hit }</span>
			  	${vo.no }. ${vo.title }
			  </div>
			  <div class="card-body">
			  	<pre>${vo.content }</pre>
			  </div>
			  <div class="card-footer">
			  	<span class="float-right">
			  		<fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/>
			  	</span>
			  	${vo.writer }
			  </div>
			</div>
	  </div>
	  <div class="card-footer">
	  	<button class="btn btn-primary" id="updateBtn" data-no="${vo.no }">수정</button>
		<!-- 모달창은 열어서 비밀번호를 입력 받고 삭제하여 가는 처리 -->
	  	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete">삭제</button>
	  	<button class="btn btn-warning" >취소</button>
	  </div>
	</div>
<div>
	<jsp:include page="boardreply.jsp"></jsp:include>
</div>
</div>
<!-- 글 상세보기 card의 끝 -->


<!-- The Modal -->
<div class="modal" id="delete">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">삭제하시겠습니까?</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <form action="delete.do" method="post">
      <!-- Modal body -->
      <div class="modal-body">
       	<div class= "form-group">
	<label for="pw">비밀번호를 입력하시오</label>
	<input class = "form-control" type="hidden" name="no" value="${vo.no}" required>
	<input class = "form-control" type="password" name="pw" id="pw" required>
	</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">삭제</button>
        <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
</form>
    </div>
  </div>
</div>
</body>
</html>