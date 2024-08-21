<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 문상훈 -->
<!-- 작성일 : 2017-01-12 -->
<!-- 최종수정일 : 2024-06-28 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 개발자 작성한 title을 가져 다 사용 -->
<title>웹:<decorator:title />
</title>
<!-- Bootstrap 4 + jquery 라이브러리 등록 - CDN 방식 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- icon 라이브러리 등록 - Font Awesome4 / google -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<style type="text/css">
.container{
	padding: 30px 5px;
}
pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
/* */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 795px;
	margin-top: 80px; 
}

#welcome {
	color: grey;
	margin: 0 auto;
}


</style>
<!-- 개발자가 작성한 소스의 head 태그를 여기에 넣게 된다. title은 제외 -->
<decorator:head />

<script type="text/javascript">

$(function() {
	$(".cancelBtn").click(function() {
		history.back();
	});
});

</script>
<!-- 로그인을 했을 떄 새로운 메세지를 3초마다 불러와서 세팅해주는 처리 js -->
<c:if test="${!empty login }">
<script type="text/javascript">

$(function() {
	setInterval(function() {
		// 서버에서 새로운 메세지 데이터를 가져와서 새로운 메세지란에 표시한다.
		// console.log("3초마다 자동 실행");
		// 새로운 메세지 표시라는 곳을 선택해서 데이터를 올린다.
		$("#newMsgCnt").load("/ajax/getNewMsgCnt.do");
	}, 3000);
});

</script>
</c:if>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top">
			<a class="navbar-brand ${(empty module || module == '/main')?'active':'' }" href="/">웹</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
   				 <span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav mr-auto">				
				<!-- 주메뉴 부분 -->
				<!-- 오른쪽 부분의 내용을 오른쪽 끝에 두기 위해서 mr-* 오른쪽 마진 사용. auto : 데이터 -->
				<li class="nav-item ${(empty module || module == '/notice')?'active':'' }"><a class="nav-link" href="/notice/list.do">공지사항</a></li>
				<li class="nav-item ${(empty module || module == '/shop')?'active':'' }"><a class="nav-link" href="/shop/list.do">쇼핑몰</a></li>
				<li class="nav-item ${(empty module || module == '/image')?'active':'' }"><a class="nav-link" href="/image/list.do">Galley</a></li>
				<li class="nav-item ${(empty module || module == '/board')?'active':'' }"><a class="nav-link" href="/board/list.do">일반게시판</a></li>
				<li class="nav-item ${(empty module || module == '/qna')?'active':'' }"><a class="nav-link" href="/qna/list.do">질문답변</a></li>
				
				<!-- 회원(로그인) 메뉴 -->
				<c:if test="${!empty login }">
				<li class="nav-item ${(empty module || module == '/message')?'active':'' }"><a class="nav-link" href="/message/list.do">메세지</a></li>
				</c:if>
				<!-- 관리자 메뉴 -->
				<c:if test="${!empty login && login.gradeNo == 9 }">
				<li class="nav-item ${(empty module || module == '/member')?'active':'' }"><a class="nav-link" href="/member/list.do">회원관리</a></li>
				</c:if>
			</ul>
			
			<c:if test="${empty login }">
			<!-- 로그인을 안했을 때  -->
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="/member/loginForm.do">
						<i class ="fa fa-sign-in"></i>
						로그인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/writeForm.do">
						<i class ="fa fa-vcard"></i>회원가입</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/S.do">
						<i class ="fa fa-search"></i>아이디/비밀번호 찾기</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${!empty login }">
			<!-- 로그인을 했을 때  -->
				<ul class="navbar-nav">
					<!-- 새로운 메세지 처리 -->
					<li class="nav-item">
						<span class="nav-link">
							<span class="badge badge-pill badge-danger" id = "newMsgCnt">
							 	${(login.newMsgCnt) }
							</span>
						<c:if test="${!empty login.photo }">
							<img alt="사진" src="${login.photo }" style="width: 35px; height: 35px;" class = "rounded-circle">
						</c:if>
						</span>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/logout.do">
						<i class ="fa fa-sign-out"></i>
						로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/">
						<i class ="fa fa-user-circle-o"></i>내정보 보기</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/">
						<i class ="	fa fa-shopping-cart"></i>장바구니</a>
					</li>
				</ul>
			</c:if>
			</div>
		</nav>

	</header>
	<article>
		<!-- 여기에 개발자 작성한 body 태그 안에 내용이 들어온다. -->
		<decorator:body />
	</article>
	<footer class="container-fluid text-center">
		<p>이 홈페이지의 저작권은 문상훈에게 있습니다.</p>
	</footer>

	
		<!-- msg를 표시할 모달창 -->
		<!-- The Modal -->
		<div class="modal" id="msgModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">처리 결과 모달 창</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">${msg }</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>

	<c:if test="${ !empty msg}">
		<!-- 모달을 보이게 하는 javascript -->
		<script type="text/javascript">
		$(function() {
			
				$("#msgModal").modal("show");
		
			
		});
	</script>
	</c:if>
</body>
</html>
