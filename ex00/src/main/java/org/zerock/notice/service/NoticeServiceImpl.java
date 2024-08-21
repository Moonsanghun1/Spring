package org.zerock.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;
import org.zerock.boardreply.mapper.BoardReplyMapper;
import org.zerock.boardreply.vo.BoardReplyVO;
import org.zerock.notice.mapper.NoticeMapper;
import org.zerock.notice.vo.NoticeVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Qualifier("noticeServiceImpl")
@Log4j
public class NoticeServiceImpl implements NoticeService{
	
	@Inject
	private NoticeMapper mapper;
	
	@Override
	public List<NoticeVO> list() {
		
		log.info("list()........");
		
		return mapper.list();
	}
	

	
}
