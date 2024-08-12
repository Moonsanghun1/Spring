<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 게시판 상세보기</h1>
<table>
	<tr>
		<th>번호</th>
		<td>${vo.no	 }</td>
	</tr>	
	<tr>
		<th>제목</th>
		<td>${vo.title }</td>
	</tr>		
	<tr>
		<th>내용</th>
		<td>${vo.content }</td>
	</tr>		
	<tr>
		<th>작성자</th>
		<td>${vo.writer }</td>
	</tr>	
	<tr>
		<th>작성일</th>
		<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></span></td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${vo.hit }</td>
	</tr>

</table>
<button onclick="location= 'updateForm.do'" >수정</button>
<button onclick="location= 'list.do'" >리스트</button>
<form action="delete.do" method="post">
<button>삭제</button>
</form>
</body>
</html>