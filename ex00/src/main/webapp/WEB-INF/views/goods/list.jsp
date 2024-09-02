<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav"  tagdir="/WEB-INF/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 리스트</title>

<style type="text/css">
.dataRow:hover{
opacity: 20%;
cursor: pointer;
}

.imageDiv{
	background:black;
}
.title{
	height: 60px;
}
</style>
<script type="text/javascript">
$(function() {
	
	// 제목 해당 태그 중 제일 높은 것을 이용하여 모두 맞추기
	// console.log($(".title"));
	let titleHeights = [];
	
	$(".title").each(function(idx, title){
		console.log($(title).height());
		titleHeights.push($(title).height());
	});
	console.log(titleHeights.join(", "));
	
	let maxTitleHeight = Math.max.apply(null, titleHeights);
	console.log(maxTitleHeight);
	
	$(".title").height(maxTitleHeight);
	
	// 이미지 사이즈 조정 5:4
	let imgWidth = $(".imageDiv:first").width();
	let imgHeight = $(".imageDiv:first").height();
	console.log("image width= " + imgWidth + ", image Height= " + imgHeight);
	// 높이 계산 = 너비는 동일하다. : 이미지와 이미지를 감싸고 있는 div의 높이로 사용
	let height= imgWidth / 5 * 4;
	// 전체 ImageDiv의 높이를 조정한다.
	$(".imageDiv").height(height);
	// 이미지 배열로 처리하지 않는다. foreach를 사용한다. - jquery each()
	$(".imageDiv > img").each(function(idx, image){		
	// 이미지가 계산된 높이보다 크면 줄인다.
		if($(image).height() > height){
			let image_width = $(image).width();
			let image_height = $(image).height();
			let width = height  / image_height * image_width;
			
			console.log("chaged image width = " + width);
			
			// 이미지 높이 줄이기
			$(image).height(height);
			// 이미지 너비 줄이기
			$(image).width(width);
		}	
	});
	
	// 이벤트 처리
	$(".dataRow").click(function() {
// 		alert("click");
// 		글번호 필요 - 수집
		let no = $(this).find(".no").text();
		console.log("no = " + no );
		location="view.do?no=" + no + "&inc=1&${pageObject.pageQuery}" ;
	});
	
	
	
	$("#perPageNum").change(function() {
		//alert("change perPageNum");
		// page는 1페이지 + 검색 데이터를 전부 보낸다
		$("#searchForm").submit();
	});
	// 검색 데이터 세팅
	$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
	$("#perPageNum").val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
});
	</script>
</head>
<body>
<div class = "container">
	<h1>이미지 게시판 리스트</h1>
	<form action="list.do" id = "searchForm">
		<input name = "page" value="1" type="hidden">
			<div class="row">
				<div class="col-md-8">
					<div class="input-group mb-3">
						<div class="input-group-prepend">

							<select name="key" id="key" class="form-control">
								<option value="t">제목</option>
								<option value="c">내용</option>
								<option value="tc">제목/내용</option>
								<option value="f">파일명</option>
							</select>

						</div>
						<input type="text" class="form-control" placeholder="검색" id="word" value = "${pageObject.word }"
							name="word">
						<div class="input-group-append">
							<button class = "btn btn-outline-primary">검색</button>
						</div>
					</div>
				</div>
				<!-- col-md-8의 끝 : 검색 -->
				<div class="col-md-4">
				<!-- 너비를 조정하여 오른쪽으로 정렬하기 위한 div. class = "float-right" : 오른쪽 정렬(CSS) -->
				<div style="width: 175px;" class = "float-right" >
				<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class = "input-group-text">Rows/Page</span>
							

						</div>
						<select id = "perPageNum" name = "perPageNum" class="form-control" >
						 	<option>8</option>
						 	<option>12</option>
						 	<option>16</option>
						 	<option>20</option>
						</select>
					</div>
				</div>
				</div>
				<!-- col-md-4의 끝 : 한 페이지당 표시 데이터 개수 -->
			</div>
		</form>
 	<c:if test="${empty list }">
 		<div class = "jumbotron">
 			<h4>데이터가 존재하지 않습니다.</h4>
 		</div>
	</c:if>
 	<c:if test="${!empty list }">
 		<div class="row">
 		<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 시작 -->
	 		<c:forEach items="${list }" var="vo" varStatus="vs">
	 			<!-- 줄바굼 처리 - 찍는 인덱스 번호가 3의 배수이면 줄바꿈을 한다. -->
	 			<c:if test="${(vs.index != 0) && (vs.index % 4 == 0 ) }">
	 			 ${"</div>"}
	 			 ${"<div class='row'>"}
	 			</c:if>
	 			<!-- 데이터 표시 시작 -->
	  			<div class="col-md-4 dataRow" >
	  				<div class="card" style="width:100%">
	  				<div class = "imageDiv text-center align-content-center" >
					  	<img class="card-img-top" src="${vo.image_name }" alt="image">
					</div>  	
					  	<div class="card-body">
					    	<h5 class="card-title">
					    		<span class= "float-right">${vo.hit }</span>
								${vo.goods_name }
							</h5>
					    	<p class="card-text text-truncate title">
					    		<div>
					    			상품 번호 : <span class="goods_no">${vo.goods_no }</span>
					    		</div>
					    		<div>
					    			정가 : <fmt:formatNumber value="${vo.price }"/>원
					    		</div>
					    		<div>
					    			할인가 : <fmt:formatNumber value="${vo.saved_rate }"/>원
					    		</div>
					    			
					    	</p>
					  	</div>
					</div>
	  			</div>
	 			<!-- 데이터 표시 끝 -->
	 		 </c:forEach>		
 		<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 끝 -->	
		</div>
		
	</c:if>
	
	<div> 
	<pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav>
	</div>
<%-- 	<c:if test="${!empty login }"> --%>
	<!-- 로그인이 되어있으면 보이게 하자. -->
	<a href="writeForm.do?perPageNum=${pageObject.perPageNum }"><button class="btn btn-primary">등록</button></a>
<%-- 	</c:if> --%>
</div>
</body>
</html>