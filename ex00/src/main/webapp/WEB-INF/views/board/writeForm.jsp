<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글 작성 폼</title>
</head>
<body>
<h1>일반 게시판 글 작성 폼</h1>
<form action="write.do" method="post">
	제목
	<input type="text" name="title" id="title"> <br>
	작성자
	<input type="text" name="writer" id="writer"> <br>
	내용
	<textarea name="content" id="content"></textarea><br>
	비밀번호
	<input type="password" name="pw" id="pw"> <br>
	<button onclick="location='list.do'">취소</button>
	<button>등록</button>
</form>
</body>
</html>