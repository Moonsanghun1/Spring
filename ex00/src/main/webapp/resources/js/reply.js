/**
 * 일반 게시판 댓글 처리
 */
 
 // 이곳이 실행되는지 확인 가능
 
 console.log("Board reply ----------------------")
 
 // 일반 게시판 댓글을 처리하는 객체 선언 - jquery의 ajax 사용 : ajax(), getJSON(), get(), post(), load()
 let replyService = {
 	
 	// 일반 게시판 댓글 리스트 처리를 위한 함수 - replyService.list(page);
 	"list" : function(page) {
 		console.log("Board reply list----------------------");
 		// page가 없으면 1로 세팅한다.
		if(!page) page = 1; 		
 		console.log("page : " + page + ", no : " + no);
 		
 		// ajax 형태를 만들어 처리한다. - getJSON()
 		$.getJSON(
 			"/boardreply/list.do?page="+page+ "&no="+no, 
 			// 데이터 가져오기를 성공하면 실행되는 함수. Data가 서버에서 넘겨주는 JSON 데이터
 			function(data){
 				console.log(data);
 				console.log(JSON.stringify(data));
 			}
 		);
 	},
 	// 일반 게시판 댓글 등록 처리를 위한 함수 - replyService.write(댓글 객체, 성공함수, 실패함수);
 	"write" : function(reply, callback, error) {
 		console.log("Board reply write----------------------")
 	},
 	// 일반 게시판 댓글 수정 처리를 위한 함수 - replyService.update(댓글 객체, 성공함수, 실패함수);
 	"update" : function(reply, callback, error) {
 		console.log("Board reply update----------------------")
 	},
 	// 일반 게시판 댓글 삭제 처리를 위한 함수 - replyService.delete(rno, 성공함수, 실패함수);
 	"delete" : function(rno, callback, error) {
 		console.log("Board reply delete----------------------")
 	},
 	
 };