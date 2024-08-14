<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.dataRow>.card-header{
	background: #e0e0e0;
}

.dataRow:hover{
	cursor: pointer;
	border-color: green;
}

</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript">
	
	$(function() {
		
		// 이벤트 		
		// 글보기 이동 처리
		$(".dataRow").click(function() {
			
			
			let no = $(this).data("no");
			location = "view.do?no="+no+"&inc=1";
		});
	})
	
</script>

<jsp:include page="../jsp/webLib.jsp"></jsp:include>
</head>
<body>
<div class="container">
<div class="card">
  <div class="card-header"><h2>일반 게시판 리스트</h2></div>
  <div class="card-body">
  	<c:forEach items="${list }" var="vo">
		<div class="card dataRow" data-no=${vo.no	 }>
			<div class="card-header">
				<span class = "float-right">조회수 : ${vo.hit }</span>
				글번호 : ${vo.no }
		  	</div>
		  	<div class="card-body">
		  		<pre>${vo.title }</pre>
		  	</div>
		  	<div class="card-footer">
		  		<span class = "float-right">
		  		<fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></span>
		  		${vo.writer }
		  	</div>
		</div>
  	</c:forEach>
  </div>
  <div class="card-footer">
  <div>
  	<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" />
  </div>
<a href="writeForm.do" class= "btn btn-primary">글등록</a></div>
</div>

</div>
</body>
</html>