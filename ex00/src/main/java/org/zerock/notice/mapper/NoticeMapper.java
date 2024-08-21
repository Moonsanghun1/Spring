package org.zerock.notice.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.notice.vo.NoticeVO;
@Repository
public interface NoticeMapper {
	
	
	public List<NoticeVO> list();
	


}
