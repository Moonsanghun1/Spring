package org.zerock.member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.member.vo.LoginVO;
import org.zerock.member.vo.MemberVO;

import com.webjjang.util.page.PageObject;

@Repository
public interface MemberMapper {
	
	
	public List<MemberVO> list(PageObject pageObject);
	// 회원 관리 리스트 페이지 처리를 위한 전체 데이터 개수
	public Long getTotalRow(PageObject pageObject);
	
	public Integer write(MemberVO vo);
	
	public Integer writeTx(MemberVO vo);
	
	public MemberVO view(Long no);
	
	public Integer inc(Long no);

	public Integer update(MemberVO vo);
	
	public Integer delete(MemberVO vo);

	public LoginVO login(LoginVO vo);

}
