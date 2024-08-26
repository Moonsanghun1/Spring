package org.zerock.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.member.mapper.MemberMapper;
import org.zerock.member.vo.LoginVO;
import org.zerock.member.vo.MemberVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("memberServiceImpl")
public class MemberServiceImpl implements MemberService{
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> list(PageObject pageObject) {
		log.info("list() 실행");
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 게시글 전체 개수 구하기
		return mapper.list(pageObject);
	}
	// @Transactional - isert 2번이 성공을 해야 commit한다. 한개라도 오류가 나면 rollback.
	@Transactional 
	@Override
	public Integer write(MemberVO vo) {
		Integer result = mapper.write(vo); // 글번호를 시퀀스에서 새로운 번호 사용
		//vo.setNo(10000L);
		//mapper.writeTx(vo); // 위에서 사용한 글번호 재사용 - PK 예외 발생
		return result; 
	}
	@Override
	public MemberVO view(Long no, int inc) {
		log.info("view() 실행");
		if(inc==1) mapper.inc(no);
		return mapper.view(no);
	}
	@Override
	public Integer update(MemberVO vo) {
		log.info("update() 실행");
		return mapper.update(vo);
	}
	@Override
	public Integer delete(MemberVO vo) {
		log.info("delete() 실행");
		return mapper.delete(vo);
	}
	
	@Override
	public LoginVO login(LoginVO vo) {
		log.info("delete() 실행");
		return mapper.login(vo);
	}
}
