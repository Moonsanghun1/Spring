package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("boardServiceImpl")
public class BoardServiceImpl implements BoardService{
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list(PageObject pageObject) {
		log.info("list() 실행");
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 게시글 전체 개수 구하기
		return mapper.list(pageObject);
	}
	@Override
	public Integer write(BoardVO vo) {
		log.info("write() 실행");
		return mapper.write(vo);
	}
	@Override
	public BoardVO view(Long no, int inc) {
		log.info("view() 실행");
		if(inc==1) mapper.inc(no);
		return mapper.view(no);
	}
	@Override
	public Integer update(BoardVO vo) {
		log.info("update() 실행");
		return mapper.update(vo);
	}
	@Override
	public Integer delete(BoardVO vo) {
		log.info("delete() 실행");
		return mapper.delete(vo);
	}
}
