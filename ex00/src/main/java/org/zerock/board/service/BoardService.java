package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("boardService")
public class BoardService {
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private BoardMapper mapper;
	
	public List<BoardVO> list() {
		log.info("list() 실행");
		return mapper.list();
	}
	public Integer write(BoardVO vo) {
		log.info("write() 실행");
		return mapper.write(vo);
	}
	public BoardVO view(Long no, int inc) {
		log.info("view() 실행");
		if(inc==1) mapper.inc(no);
		return mapper.view(no);
	}
}
