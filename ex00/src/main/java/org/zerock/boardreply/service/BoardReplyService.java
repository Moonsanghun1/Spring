package org.zerock.boardreply.service;

import java.util.List;

import org.zerock.boardreply.vo.BoardReplyVO;

import com.webjjang.util.page.PageObject;

public interface BoardReplyService {
	
	
	// 1. list
	// 메소드에 처리되는 데이터는 기본이 1개이다. 2개이상인 경우 @Param을 사용, map으로 만들어서 1개를 넘긴다.
	public List<BoardReplyVO> list(PageObject pageObject, Long no);
	// 2. write
	public Integer write(BoardReplyVO vo);
	// 3. update
	public Integer update(BoardReplyVO vo);
	// 4. delete
	public Integer delete(BoardReplyVO vo);
	
	
}
