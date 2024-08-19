package org.zerock.boardreply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;
import org.zerock.boardreply.mapper.BoardReplyMapper;
import org.zerock.boardreply.vo.BoardReplyVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("BoardReplyServiceImpl")
public class BoardReplyServiceImpl implements BoardReplyService{
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private BoardReplyMapper mapper;
	
	@Override
	public List<BoardReplyVO> list(PageObject pageObject, Long no) {
		log.info("list() 실행");
		pageObject.setTotalRow(mapper.getTotalRow(pageObject, no));
		// 게시글 전체 개수 구하기
		return mapper.list(pageObject, no);
	}
	@Override
	public Integer write(BoardReplyVO vo) {
		log.info("write() 실행");
		return mapper.write(vo);
	}
	@Override
	public Integer update(BoardReplyVO vo) {
		log.info("update() 실행");
		return mapper.update(vo);
	}
	@Override
	public Integer delete(BoardReplyVO vo) {
		log.info("delete() 실행");
		return mapper.delete(vo);
	}
	
}
