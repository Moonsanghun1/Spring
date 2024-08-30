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
<script type="text/javascript">
	
	$(function() {
		
		// 이벤트 		
		// 글보기 이동 처리
		$(".dataRow").click(function() {
			
			
			let no = $(this).data("no");
			location = "view.do?no="+no;
		});
	})
	
</script>

</head>
<body>
<div class="container">
<div class="card">
  <div class="card-header"><h2>공지사항 리스트</h2></div>
  <div class="card-body">
  	<c:forEach items="${list }" var="vo">
		<div class="card dataRow" data-no=${vo.no	 }>
			<div class="card-header">
				<span class = "float-right"></span>
				글번호 : ${vo.no }
		  	</div>
		  	<div class="card-body">
		  		<pre>${vo.title }</pre>
		  	</div>
		  	<div class="card-footer">
		  		<span class = "float-right">
		  		<fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></span>
		  		
		  	</div>
		</div>
  	</c:forEach>
  </div>
  <div class="card-footer">
 
<a href="writeForm.do" class= "btn btn-primary">글등록</a></div>
</div>

</div>
</body>
</html>