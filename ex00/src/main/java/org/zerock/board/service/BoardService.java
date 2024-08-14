package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;


public interface BoardService {
	
	public List<BoardVO> list(PageObject pageObject);
	
	public Integer write(BoardVO vo);
	
	public BoardVO view(Long no, int inc);
	
	public Integer update(BoardVO vo);
	
	public Integer delete(BoardVO vo);
	
}
