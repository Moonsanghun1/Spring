<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글보기</title>

<style type="text/css">
	#smallImageDiv img {
		width: 80px;
		height: 80px;
		margin: 3px;
	}
	
	#smallImageDiv img:hover {
		opacity: 70%;
		cursor: pointer;
	}
	#goodsDetailDiv div{
		padding: 5px;
		border-bottom: 1px solid #444; 
	}
</style>

<script type="text/javascript">
$(function(){
	
	// 이벤트 처리
	$("#updateBtn").click(function(){
		let no = $(this).data("no");
		// alert(no);
		location = "updateForm.do?no=" + no + "&inc=0";
	});
	
	$("#delete").click(function(){
		$("#pw").val("");
	});
	$("#listBtn").click(function(){
		location = "list.do?page=${param.page}&perPageNum=${param.perPageNum}";
	});
	
	// 이미지 보기 작은 사진 클릭 -> 크게 보기
	$("#smallImageDiv img").click(function() {
		$("#bigImageDiv img").attr("src", $(this).attr("src"));
	})
});


</script>

</head>
<body>
<div class="container">
	<div class="card">
	  <div class="card-header"><h2>살품 상세보기</h2></div>
	  <div class="card-body">
		<div class="row">
			<div class="col-md-6">
				<div id="smallImageDiv" class="img-thumbnalil">
					<img src="${vo.image_name }">
					<c:if test="${!empty imageList }">
						<c:forEach items="${imageList }" var="ImageVO">
							<img src="${ImageVO.image_name }" class="img-thumbnalil">
						</c:forEach>
					</c:if>
				</div>
				<div id="bigImageDiv" class="img-thumbnalil">
					<img src="${vo.image_name }" style="width: 100%;">
				</div>
			</div>
			<div class="col-md-6" id="goodsDetailDiv">
				<div><i class="fa fa-check"></i>분류 : ${vo.cate_name }</div>
				<div><i class="fa fa-check"></i>상품명 : ${vo.goods_name }</div>
				<div><i class="fa fa-check"></i>상품 번호 : ${vo.goods_no }</div>
				<div><i class="fa fa-check"></i>제조사 : ${vo.company }</div>
				<div><i class="fa fa-check"></i>제조년월일 : <fmt:formatDate value="${vo.product_date }" pattern="yyyy-MM-dd"/></div>
				<div><i class="fa fa-check"></i>정가 : <fmt:formatNumber value="${vo.price }"/>원</div>
				<div><i class="fa fa-check"></i>할인가 : <fmt:formatNumber value="${(empty vo.discount)?0:vo.discount }" /> 원</div>
				<div><i class="fa fa-check"></i>할인률 : ${(empty vo.discount_rate)?0:vo.discount_rate } %</div>
				<div>
	  				<i class="fa fa-check"></i>
	  				적립금 : 
	  				<fmt:formatNumber 
	  					value="${(empty vo.saved_rate)?0:(vo.price * vo.saved_rate/ 100) }" /> 원
	  				(${(empty vo.saved_rate)?0:vo.saved_rate } %)
	  			</div>
				<div style="color: red;"><i class="fa fa-check"></i>판매가 : <fmt:formatNumber value="${vo.sale_price }"/>원</div>
				<div><i class="fa fa-check"></i>배송비 : <fmt:formatNumber value="${vo.delivery_charge }"/>원
				(5만원 이상 구매하실 경우 무료 배송이 됩니다.)
				</div>
			</div>
		</div>	
		<div class="row">	
			<div class="col-md-12" >
				<form action="/cart/write.do" method="post">
					<c:if test="${!empty sizeColorList }">
						<div class="form-inline">
						  <div class="input-group mb-3 input-group-sm">
						     <div class="input-group-prepend">
						       <span class="input-group-text">size/color</span>
						    </div>
						    <select class="form-control">
						    	<option value="0">size/color 선택</option>
						    	<c:forEach items="${sizeColorList }" var="sizeColorVO">
						    		<option value="${sizeColorVO.size_no }">${sizeColorVO.size_name}
						    		<c:if test="${!empty sizeColorVO.color_no && sizeColorVO.color_no != 0 }">
						    			/ ${sizeColorVO.color_name }
						    		</c:if>
						    		</option>
						    	</c:forEach>
						    </select>
						    <div class="input-group-append">
						    	<button type="button" class="btn btn-outline-secondary">+</button>
						    </div>
						  </div>
						</div>
					</c:if>
					
					<c:if test="${!empty optionList }">
					</c:if>
					
					<c:if test="${empty optionList and empty sizeColorList}">
					</c:if>
				</form>
			</div>
		</div>
	  </div>
	  <div class="card-footer">
	  	<button class="btn btn-primary" id="updateBtn" data-no="${vo.goods_no }">수정</button>
		<!-- 모달창은 열어서 비밀번호를 입력 받고 삭제하여 가는 처리 -->
	  	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete">삭제</button>
	  	<button class="btn btn-warning" >리스트</button>
	  </div>
	</div>

</div>
<!-- 글 상세보기 card의 끝 -->


<!-- The Modal -->
<div class="modal" id="delete">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">삭제하시겠습니까?</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <form action="delete.do" method="post">
      <!-- Modal body -->
      <div class="modal-body">
       	<div class= "form-group">
	<label for="pw">비밀번호를 입력하시오</label>
	<input class = "form-control" type="hidden" name="no" value="${vo.goods_no}" required>
	<input class = "form-control" type="password" name="pw" id="pw" required>
	</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">삭제</button>
        <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
</form>
    </div>
  </div>
</div>
</body>
</html>