<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글 작성 폼</title>
</head>
<body>
<div class="container">
<h1>일반 게시판 글 작성 폼</h1>
<form action="write.do" method="post">
	<div class= "form-group">
	<label for="title">제목</label>
	<input type="text" name="title" class = "form-control"  id="title" required>
	</div>

	<div class= "form-group">
	<label for="content">내용</label>
	<textarea class = "form-control"  name="content" id="content" required></textarea>
	</div>

	
	<div class= "form-group">
	<label for="writer">작성자</label>
	<input class = "form-control" type="text" name="writer" id="writer" required>
	</div>
	
	<div class= "form-group">
	<label for="pw">비밀번호</label>
	<input class = "form-control" type="password" name="pw" id="pw" required>
	</div>
	
	<div class= "form-group">
	<label for="pw2">비밀번호 확인</label>
	<input class = "form-control" type="password" id="pw2" required>
	</div>
	
	<button type="submit">등록</button>
	<button type="reset">재입력</button>
	<button class="cancelBtn">취소</button>
</form>
</div>
</body>
</html>