package org.zerock.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.page.PageObject;

@Repository
public interface BoardMapper {
	
	
	public List<BoardVO> list(PageObject pageObject);
	// 일반 게시판 리스트 페이지 처리를 위한 전체 데이터 개수
	public Long getTotalRow(PageObject pageObject);
	
	public Integer write(BoardVO vo);
	
	public BoardVO view(Long no);
	
	public Integer inc(Long no);

	public Integer update(BoardVO vo);
	
	public Integer delete(BoardVO vo);


}
