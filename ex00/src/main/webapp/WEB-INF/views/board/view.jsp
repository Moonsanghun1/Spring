<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<td>1</td>
	</tr>	
	<tr>
		<th>제목</th>
		<td>안녕</td>
	</tr>		
	<tr>
		<th>작성자</th>
		<td>문상훈</td>
	</tr>	
	<tr>
		<th>작성일</th>
		<td>2024.07.01</td>
	</tr>

</table>
<button onclick="location= 'updateForm.do'" >수정</button>
<button onclick="location= 'list.do'" >리스트</button>
<form action="delete.do" method="post">
<button>삭제</button>
</form>
</body>
</html>