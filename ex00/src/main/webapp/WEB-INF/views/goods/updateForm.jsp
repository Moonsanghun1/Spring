<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글 수정 폼</title>
</head>
<body>
<h1>일반 게시판 글 수정 폼</h1>
<form action="update.do" method="post">
	번호
	<input type="text" name="no" id="no" value="${vo.no }" readonly="readonly"> <br>
	제목
	<input type="text" name="title" id="title" value="${vo.title }"> <br>
	작성자
	<input type="text" name="writer" id="writer" value="${vo.writer }"> <br>
	내용
	<textarea name="content" id="content" >${vo.content }</textarea><br>
	비밀번호
	<input type="password" name="pw" id="pw"> <br>
	<button>글 수정 하기</button>
	<button onclick="location='list.do'"  >취소하기</button>
</form>
</body>
</html>