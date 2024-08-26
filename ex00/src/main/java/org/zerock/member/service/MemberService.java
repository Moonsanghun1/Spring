package org.zerock.member.service;

import java.util.List;

import org.zerock.member.vo.LoginVO;
import org.zerock.member.vo.MemberVO;

import com.webjjang.util.page.PageObject;


public interface MemberService {
	// 회원 리스트
	public List<MemberVO> list(PageObject pageObject);
	// 회원 가입
	public Integer write(MemberVO vo);
	// 보기
	public MemberVO view(Long no, int inc);
	// 수정
	public Integer update(MemberVO vo);
	// 탈퇴
	public Integer delete(MemberVO vo);
	// 로그인
	public LoginVO login(LoginVO vo);
}
