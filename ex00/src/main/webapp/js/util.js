/**
*  JS Utility 프로그램
*/

/**
* "pageObject":{"page":1,"perPageNum":10,"startRow":1,"endRow":10,
* "perGroupPageNum":10,"startPage":1,"endPage":1,"totalPage":1,"totalRow":0,"key":null,"word":null,"period":"pre",
* "accepter":null,"acceptMode":1,"notPageQuery":"perPageNum=10&key=&word=",
* "pageQuery":"page=1&perPageNum=10&key=&word=","limit":0}}
*/
// 댓글 페이지 네이션 테그들의 문자열을 만들어 넘겨준다.
function replyPagination(pageObject){
	let str = ""; 
		
		str +=  "<li class=\"page-item";
		
		// disabled 추가 여부 - 이전페이지가 없으면 disable로 한다.
		if(pageObject.startPage == 1) str += " disabled ";
		str +=	"\" data-page = \"" + (pageObject.startPage -1) + "\"><a class=\"page-link\" href=\"#\">Previous</a></li>";
		
		// startPage 부터 endPage 까지 반복 처리하면 페이지 만들어 내기
		for(i = pageObject.startPage; i <= pageObject.endPage; i++){
		 	str += "<li class=\"page-item";
		 	if(i == pageObject.page) str += " active ";
		 	str += "\"data-page = \"" + i + "\"><a class=\"page-link\" href=\"#\">" + i + "</a></li>";
		}
		
		// 다음 페이지 - endPage가 10이면 11로 보내는 처리
		str +=  "<li class=\"page-item"
		if(pageObject.endPage >= pageObject.totalPage) str += " disabled ";
		str +=	"\" data-page = \"" + (pageObject.endPage + 1) + "\"><a class=\"page-link\" href=\"#\">Next</a></li>";
		
		return str;
}

