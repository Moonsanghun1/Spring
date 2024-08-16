<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
$(function() {
	
let json ='[{"SampleDTO":{"name":"홍길동","age":10}},{"SampleDTO":{"name":"관리자","age":30}}]';;
	$("#dataTable").text(json);
	console.log(JSON.parse(json));
	let obj = JSON.parse(json);
	for(var i in obj) {
		$("#row").html('<tr><td>'+ obj.name +'</td><td>'+ obj.age +'</td></tr>')
		 
    };
	
});

</script>
</head>
<body>
<h1>use json</h1>
<div class="container">
  <h2>Basic Table</h2>
  <p>The .table class adds basic styling (light padding and horizontal dividers) to a table:</p>            
  <table class="table">
    <thead>
      <tr>
        <th>name</th>
        <th>age</th>
      </tr>
    </thead>
    <tbody id = "row">

    </tbody>
  </table>
</div>

</body>
</html>