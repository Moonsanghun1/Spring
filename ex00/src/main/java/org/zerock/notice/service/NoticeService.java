package org.zerock.notice.service;

import java.util.List;

import org.zerock.notice.vo.NoticeVO;

public interface NoticeService {
	
	public List<NoticeVO> list();
	public NoticeVO view(Long no);
	public Integer write(NoticeVO vo);
	
}
