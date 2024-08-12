package org.zerock.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.board.vo.BoardVO;

@Repository
public interface BoardMapper {
	
	
	public List<BoardVO> list();
	
	public Integer write(BoardVO vo);
	
	public BoardVO view(Long no);
	public Integer inc(Long no);

}
