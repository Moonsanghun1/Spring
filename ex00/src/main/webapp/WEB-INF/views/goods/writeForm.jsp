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
	// 바로 사이즈의 데이터를 세팅한다. 
	$("#sizeDiv").load("/ajax/getSize.do?cate_code1="+$("#cate_code1").val());
	// 바로 색상의 데이터를 세팅한다. 
	$("#colorDiv").load("/ajax/getColor.do?cate_code1="+$("#cate_code1").val());
	
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
	
	let appendImageTag="";
	appendImageTag += "<div class=\"input-group mb-3\">";
	appendImageTag += "<input class=\"form-control\" type=\"file\" name=\"imageFiles\"> ";
	appendImageTag += "<div class=\"input-group-append\">";
	appendImageTag += "	<button type=\"button\" class=\"btn btn-danger removeImageBtn\">";
	appendImageTag += "     	<i class=\"fa fa-close\"></i>";
	appendImageTag += "    </button>";
	appendImageTag += "</div>";
	appendImageTag += " </div>";
	
	let imageCnt =1;
	
	// 첨부 이미지 추가 처리
	$("#addImageBtn").click(function() {
		if (imageCnt >= 5) {
			alert("첨부이미지는 최대 5개까지만 가능합니다");
			return false;
		}
		$("#imageFiledset").append(appendImageTag);
		imageCnt ++;
	});
	
	// 첨부 이미지 제거 처리
	$("#imageFiledset").on("click", ".removeImageBtn", function() {
	
		$(this).closest(".input-group").remove();
		imageCnt --;
	});
	
	let appendOptionTag="";
	appendOptionTag += "<div class=\"input-group mb-3\">";
	appendOptionTag += "<input class=\"form-control\" type=\"text\" name=\"option_names\"> ";
	appendOptionTag += "<div class=\"input-group-append\">";
	appendOptionTag += "	<button type=\"button\" class=\"btn btn-danger removeOptionBtn\">";
	appendOptionTag += "     	<i class=\"fa fa-close\"></i>";
	appendOptionTag += "    </button>";
	appendOptionTag += "</div>";
	appendOptionTag += "</div>";
	
	let optionCnt =1;
	
	// 옵션 추가 처리
	$("#addOptionBtn").click(function() {
		if (optionCnt >= 5) {
			alert("첨부이미지는 최대 5개까지만 가능합니다");
			return false;
		}
		$("#optionFieldset").append(appendOptionTag);
		optionCnt ++;
	});
	
	// 옵션 제거 처리
	$("#optionFieldset").on("click", ".removeOptionBtn", function() {
		$(this).closest(".input-group").remove();
		imageCnt --;
	});
	
	// 사이즈나 색상의 체크박스를 클릭하면 처리
	$("#sizeFieldset, #colorFieldset").on("click","input", function() {
		// option input div 지우기 - 처음 것은 제외
		$("#optionFieldset > div:not(:first)").remove();
		optionCnt= 1;
		$("#optionFieldset input").val("");	
	});
	
	// 옵션에 input 태그 클릭하면 사이즈와 컬러의 체크박스를 전체 해제시킨다.
	$("#optionFieldset").on("focusin", "input", function() {
		console.log("옵션 입력란");
		// 사이즈와 컬러의 체크박스 전부 해제 시킨다.
		$("#sizeFieldset input, #colorFieldset input").prop("checked", false);	
	});
});

</script>

</head>
<body>
<div class="container">
<h1>상품 등록</h1>
<!-- 상품 기본 정보 입력 -->
<form action="write.do" method="post" enctype="multipart/form-data">
	<input type="hidden" value="${param.perPageNum }" name="perPageNum">
	<fieldset class="border p-4">
	    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[상품 카테고리 입력]</b></legend>
	 
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
  </fieldset>
  
  <fieldset class="border p-4">
	    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[상품 기본 정보 입력]</b></legend>
	 
	
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
		<input class = "form-control" type="file" id="detailImageFile" name="detailImageFile" required>
	</div>
	
	<div class= "form-group">
		<label for="content">상세 설명</label>
		<textarea class = "form-control"  name="content" id="content" rows="7" ></textarea>
	</div>
	
	<div class= "form-group">
		<label for="image_name">대표 이미지</label>
		<input class = "form-control" type="file" id="imageFile" name="imageFile" required>
	</div>
	</fieldset>
	<!-- 상품 기본 정보 입력 끝 -->
	
	 <fieldset class="border p-4">
	    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[상품 가격 정보 입력]</b></legend>
	 
	
	<!-- 가격 정보 입력 시작 -->
	<div class= "form-group">
		<label for="price">정가</label>
		<input class = "form-control" type="text"  name="price" id="price" required>
	</div>
	<div class= "form-group">
		<label for="discount">할인가</label>
		<input class = "form-control" type="text"  name="discount" id="discount" >
	</div>
	<div class= "form-group">
		<label for="discount_rate">할인율</label>
		<input class = "form-control" type="text"  name="discount_rate" id="discount_rate" >
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
		<input class = "form-control datepicker"  name="sale_startDate" id="sale_startDate" required readonly> 
	</div>
	<div class= "form-group">
		<label for="sale_endDate">판매 종료일</label>
		<input class = "form-control datepicker" name="sale_endDate" id="sale_endDate" required readonly> 
	</div>
	</fieldset>
	<!-- 가격 정보 입력 끝 -->
	<fieldset class="border p-4">
		<!-- px-# : padding 왼쪽 오른쪽 상대적인 설정 (참고:pl-#,pr-#,pt-#,pb-#,py-#) -->
	    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[상품 옵션 정보 입력]</b></legend>
	  <div class="m-4" style="color: red;">
	  	사이즈 / 색상과 옵션을 같이 사용할 수 없습니다. 사이즈나 색상을 선택하면 옵션이 사라집니다. 옵션을 선택하면 반대가 됩니다.
	  </div>
		<fieldset class="border p-4" id="sizeFieldset">
		    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[사이즈]</b></legend>
		    <div id="sizeDiv" class="form-inline"></div>
		</fieldset>
		<fieldset class="border p-4" id="colorFieldset">
		    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[색상]</b></legend>
		    <div id="colorDiv" class="form-inline"></div>
		</fieldset>
		<fieldset class="border p-4" id="optionFieldset">
		    <legend class="w-auto px-2">
		    	<b style="fron-size: 14pt;">[옵션]</b>
		    	<button type="button" id="addOptionBtn" class="btn btn-primary btn-sm">Add Option</button>
		    </legend>
		    
		    <div id="optionDiv" class="input-group">
		    	<div class="input-group mb-3">
		    		<input class="form-control" type="text" name="option_names"> 
		    	</div>
		    </div>
		</fieldset>
		
	</fieldset>
	<fieldset class="border p-4" id="imageFiledset">
	    <legend class="w-auto px-2"><b style="fron-size: 14pt;">[상품 첨부 이미지 입력]</b>
	    	<button type="button" id="addImageBtn" class="btn btn-primary btn-sm">Add Image</button>
	    </legend>
	 	  <div class="input-group mb-3">
		    <input class="form-control" type="file" name="imageFiles"> 
		  </div>
	</fieldset>
	
	<button type="submit">등록</button>
	<button type="reset">재입력</button>
	<button class="cancelBtn">취소</button>
</form>
</div>
</body>
</html>