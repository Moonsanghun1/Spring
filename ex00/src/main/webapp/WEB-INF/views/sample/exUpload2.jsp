<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/sample/exUploadPost2" method="post" enctype="multipart/form-data">

<div>
	<input type="file" name="files" multiple>	
	
</div>

<div>
	<input type="submit">
</div>

</form>

</body>
</html>