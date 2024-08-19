<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script type="text/javascript">
//#dataTable  위 xml 데이터로 table tag를 만들어서 넣어보기 
let json = [{'SampleDTO':{'name':'홍길동','age':10}},{'SampleDTO':{'name':'관리자','age':30}}];
$(function(){
	console.log(json);
	let tagStr = "<table>";
		tagStr += "<tr>";
			tagStr += "<th>이름</th>";
			tagStr += "<th>나이</th>";
		tagStr += "</tr>";
	$(json).each(
		function(){
			tagStr += "<tr>";
			// alert(JSON.stringify(this));
			// alert(this.SampleDTO.name);
			tagStr += "<td>" + this.SampleDTO.name + "</td>"
			tagStr += "<td>" + this.SampleDTO.age + "</td>"
			tagStr += "</tr>";
		}
	);
	
	tagStr += "</table>";
	
	$("#dataTable").html(tagStr);

});
</script>

</head>
<body>
<h1>use JSON</h1>
<div id="dataTable">
</div>
</body>
</html>