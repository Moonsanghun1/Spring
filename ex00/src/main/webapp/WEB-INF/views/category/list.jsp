<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>

<script type="text/javascript">
	$(function () {
		$(".majCateData").click(function(){
// 			alert("대분류 클릭");
			// 대분류 번호 가져오기
			let cate_code1 = $(this).data("cate_code1");
// 			alert("cate_code1 :"+ cate_code1);
			// 현재 대분류(active)가 아닌 경우만 페이지 이동
			if (!$(this).hasClass("active"))
				location = "list.do?cate_code1="+ cate_code1;
		});
		
			$(".editDiv").hide();
		$(".cate_edit").click(function() {
			//alert("카테고리 대분류 수정");
			// 전체적으로 다 안보이게
			
			// 클릭한 탭 아래 버튼을 보이게
			$(this).next(".editDiv").slideDown();
			
			return false; // a tag의 페이지 이동 처리를 무시시킨다.
		});

		$("#majWriteBtn").click(function(){
			// a tag의 페이지 이동처리를 무시 시킨다.
			return categoryProcess("대분류 추가", 0, 0, "","write.do", "추가");
		});
		
		$("#midWriteBtn").click(function(){
			// a tag의 페이지 이동처리를 무시 시킨다.
			return categoryProcess("중분류 추가", ${cate_code1}, 0, "", 	"write.do", "추가");

		});
		
		// 대분류 수정 버튼
		$(".majUpdateBtn").click(function() {
			//alert("대분류");
			
			let cate_code1 = $(this).closest("a").data("cate_code1");
			let cate_name = $(this).closest("a").find(".cate_name").text();			
		
			return categoryProcess("대분류 수정", cate_code1, 0, cate_name,"update.do", "수정");
		})
		
		// 대분류 삭제 버튼
		$(".majDeleteBtn").click(function() {
			//alert("대분류");
			
			let cate_code1 = $(this).closest("a").data("cate_code1");
			
			return categoryProcess("대분류 삭제", cate_code1, 0, "","delete.do", "삭제");
		})
		
		// 중분류 수정 버튼
		$(".midUpdateBtn").click(function() {
			
			
			let cate_code1 = $(this).closest("li").data("cate_code1");
			let cate_code2 = $(this).closest("li").data("cate_code2");
			let cate_name = $(this).closest("li").find(".cate_name").text();
			
			return categoryProcess("중분류 수정", cate_code1, cate_code2, cate_name,"update.do", "수정");
		})
		
		// 중분류 삭제 버튼
		$(".midDeleteBtn").click(function() {
			
			
			let cate_code1 = $(this).closest("li").data("cate_code1");
			let cate_code2 = $(this).closest("li").data("cate_code2");
			
			return categoryProcess("중분류 삭제", cate_code1, cate_code2, "","delete.do", "삭제");
			
		})
		
		function categoryProcess(title, cate_code1, cate_code2, cate_name, url, btnName) {
			// 모달 헤더 변경
			$("#categoryModal").find(".modal-title").text(title);
			// category 데이터들은 전부 0이여야 한다.
			$("#modalCate_code1").val(cate_code1);
			$("#modalCate_code2").val(cate_code2);
			$("#modalCate_name").val(cate_name);
			// 데이터를 보낸 url 세팅
			$("#modalForm").attr("action", url);
			// 버튼 이름 교체
			$("#submitBtn").text(btnName);
			
			$("#categoryModal").modal("show");
			return false;
		}
		
	});
</script>

</head>
<body>
<div class="container">
	<div class="card">
		<div class="card-header"><h2> 카테고리 관리</h2></div>
			<div class="card-body">
				<p>대분류 탭을 수정하거나 삭제하려면 오른쪽 마우스 버튼을 클릭하세요</p>
				<ul class="nav nav-tabs">
					<c:forEach items="${majList }" var="vo">
					  <li class="nav-item">
					    <a class="nav-link majCateData ${(vo.cate_code1==param.cate_code1)?'active':'' }" href="#mid_category" 
					    data-toggle="tab" data-cate_code1=${vo.cate_code1 }><span class="cate_name">${vo.cate_name }</span><i class= "fa fa-edit cate_edit"></i>
							<div class="editDiv">
								<button class="btn btn-success btn-sm majUpdateBtn">수정</button>
								<br>
								<button class="btn btn-danger btn-sm majDeleteBtn">삭제</button>
							</div> 
						</a>
					
					  </li>
					</c:forEach>
					  <li class="nav-item">
							<a class="nav-link" href="#" data-toggle="tab" id="majWriteBtn"><i class="fa fa-plus"></i></a>
					  </li>
				</ul>
				<div class="tab-content">
					<div id="mid_category" class= "container tab-pane active">
						<h3>카테고리 - 중분류
							<button class="btn btn-primary" id = "midWriteBtn"
							data-cate_code1="${cate_code1 }">추가</button>
						</h3>
						<ul class="list-group">
							<c:forEach items="${midList}" var="vo">
							<li class="list-group-item" data-cate_code1=${vo.cate_code1 } data-cate_code2=${vo.cate_code2 }>
								<span class ="cate_name">${vo.cate_name }</span>
								<span class="pull-right">
									<button class="btn btn-success btn-sm midUpdateBtn">수정</button>
									<button class="btn btn-danger btn-sm midDeleteBtn">삭제</button>
								</span>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
		</div>
		<div class="card-footer">Footer</div>
	</div>
</div>

<!-- The Modal -->
<div class="modal" id="categoryModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
	<form method="post" id="modalForm">
		<input name="cate_code1" value="0" type="hidden" id="modalCate_code1">
		<input name="cate_code2" value="0" type="hidden" id="modalCate_code2">
      
      <div class="modal-body">
		<input name="cate_name" class= "form-control" id="modalCate_name">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button class="btn btn-primary" id= "submitBtn">전송</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
	</form>
    </div>
  </div>
</div>

</body>
</html>