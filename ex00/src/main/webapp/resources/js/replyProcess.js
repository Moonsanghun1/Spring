/**
 *  replyService 객체를 이용한 댓글 처리 코드
 */
function showList(page){ 
	replyService.list(page,
		// 데이터 가져오기 성공했을 때 실행되는 함수 -> html tag를 만들어서 표시하는 함수 실행
		function(data){
			//data의 구조 - {list[], pageObject}
			let list = data.list;
			// ul tag안에 들어가는 문자열 
			let str = "";
			// 데이터가 없는 경우의 처리
			if(list==null || list.length == 0){
			$(".chat").html("<li>데이터가 존재하지 않습니다.</li>");
			return;}
			//데이터가 있는 경우의 처리
			for(let i = 0; i<list.length; i++){
				str += "<li class=\"left clearfix\" data-rno=\"" + list[i].rno + "\">";
				str +=  "<div>";
				str +=  "<div class=\"header\">";
				str +=  "<strong class = \"primary-font\">"+ list[i].name + "(" + list[i].id +")</strong>";
				str +=  "<small class=\"pull-right text-muted\">" + toDateTime(list[i].writeDate) 	+ "</small>";
				str +=  "</div>";
				str +=  "<p><pre>" + list[i].content + "</pre></p>";
				str +=  "</div>";
			  	str +=  "</li>";
		
			};
		$(".chat").html(str);
	});
};	
// 일반게시판 글보기가 처음에 보여질 때 댓글 리스트 보이기 실행
showList(1);

// HTML이 로딩이 된 상태에서 실행한다.
$(function(){
	// 이벤트 처리
	$("#replyWriteBtn").click(function(){
		//alert("ddd");
		// 댓글 등록에 필요한 데이터 (no, content) 수정
		let reply = {no: no, content : $("#replyContent").val()}
		alert(JSON.stringify(reply));
		replyService.write(reply,
			function(result){
			$("#replyModal").modal("hide");
				alert(result);
				// 댓글 리스트 데이터가 변경되었으므로 리스트를 다시 불러온다.
				showList(1);
			}
		);	
	});


});

