<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 게시판 리스트</h1>
<table>
	<tr>
		<th>일반 게시판 리스트</th>
	</tr>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<tr  onclick="location= 'view.do'">
		<td>1</td>
		<td>안녕</td>
		<td>문상훈</td>
		<td>2024.07.01</td>
	</tr>
	<tr> </tr>
</table>
<button onclick="location= 'writeForm.do'" >글쓰기</button>
</body>
</html>