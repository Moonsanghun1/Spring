<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>

<script type="text/javascript">

$(function() { // HTML 문서가 로딩이 다 되면 실행해라
	
	// 바로 중분류의 데이터를 세팅한다. 
	$("#cate_code2").load("/ajax/getMidList.do?cate_code1="+$("#cate_code1").val());
	// 제조일을 현재 날짜 이전만 입력
	$("#product_date").datepicker("option",{"maxDate" : new Date()});	
	// 판매시작일과 종료일은 현재 날짜 이후만 입력 가능
	$("#sale_startDate, #sale_endDate").datepicker("option",{"minDate" : new Date()});	
	
	// 대분류를 바꾸면 중분류도 바꿔야한다.
	$("#cate_code1").change(function() {
		$("#cate_code2").load("/ajax/getMidList.do?cate_code1="+$("#cate_code1").val());
	});
	
	$("#sale_startDate").change(function() {

		$("#sale_endDate").datepicker( "option", "minDate", $(this).val() );

	});
	$("#sale_endDate").change(function() {

		$("#sale_startDate").datepicker( "option", "maxDate", $(this).val() );

	});
	
});

</script>

</head>
<body>
<div class="container">
<h1>상품 등록</h1>
<!-- 상품 기본 정보 입력 -->
<form action="write.do" method="post" enctype="multipart/form-data">
	<div class= "form-group">
	<label for="title">대분류</label>
	<select class="form-control" name="cate_code1" id="cate_code1" style="margin: 5px;">
		<c:forEach items="${majList}" var="vo">
			<option value="${vo.cate_code1 }">${vo.cate_name }</option>
		</c:forEach>
	</select>
	</div>

	<div class="form-group">
	<label for="content">중분류</label>
		<select class="form-control" name="cate_code2" id="cate_code2" >
			
		</select>
	</div>
	
	<div class= "form-group">
		<label for="goods_name">상품명</label>
		<input type="text" name="goods_name" class = "form-control"  id="goods_name" required>
	</div>

	
	<div class= "form-group">
		<label for="company">제조사</label>
		<input class = "form-control" type="text" name="company" id="company" required>
	</div>
	
	<div class= "form-group">
		<label for="product_date">제조일</label>
		<input class = "form-control datepicker" type="text"  name="product_date" id="product_date" required readonly	>
	</div>
	
	<div class= "form-group">
		<label for="detail_image_name">상세 설명 이미지</label>
		<input class = "form-control" type="file" id="detail_image_name" name="detail_image_name" required>
	</div>
	
	<div class= "form-group">
		<label for="content">상세 설명</label>
		<textarea class = "form-control"  name="content" id="content" rows="7" ></textarea>
	</div>
	
	<div class= "form-group">
		<label for="image_name">대표 이미지</label>
		<input class = "form-control" type="file" id="image_name" name="image_name" required>
	</div>
	
	<!-- 상품 기본 정보 입력 끝 -->
	
	<!-- 가격 정보 입력 시작 -->
	<div class= "form-group">
		<label for="price">정가</label>
		<input class = "form-control" type="text"  name="price" id="price" required>
	</div>
	<div class= "form-group">
		<label for="discount">할인가</label>
		<input class = "form-control" type="text"  name="discount" id="discount" required>
	</div>
	<div class= "form-group">
		<label for="discount_rate">할인율</label>
		<input class = "form-control" type="text"  name="discount_rate" id="discount_rate" required>
	</div>
	<div class= "form-group">
		<label for="delivery_charge">배송료</label>
		<input class = "form-control" type="text"  name="delivery_charge" id="delivery_charge" required>
	</div>
	<div class= "form-group">
		<label for="saved_rate">적립율</label>
		<input class = "form-control" type="text"  name="saved_rate" id="saved_rate" required>
	</div>
	
	<div class= "form-group">
		<label for="sale_startDate">판매 시작일</label>
		<input class = "form-control datepicker" type="text"  name="sale_startDate" id="sale_startDate" required readonly> 
	</div>
	<div class= "form-group">
		<label for="sale_endDate">판매 종료일</label>
		<input class = "form-control datepicker" type="text"  name="sale_endDate" id="sale_endDate" required readonly> 
	</div>
	<!-- 가격 정보 입력 끝 -->
	
	<button type="submit">등록</button>
	<button type="reset">재입력</button>
	<button class="cancelBtn">취소</button>
</form>
</div>
</body>
</html>