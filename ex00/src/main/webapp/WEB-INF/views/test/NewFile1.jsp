<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav"  tagdir="/WEB-INF/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	
});
</script>
</head>
<body>
<div class="container">
	<div class="card">
		<div class="card-header"></div>
		<div class="card-body">
		
		</div>
		<div class="card-footer">
			<div> 
				<pageNav:pageNav listURI="list.do" pageObject="pageObject"></pageNav:pageNav>
			</div>
		</div>
	</div>	
</div>	
</body>
</html>