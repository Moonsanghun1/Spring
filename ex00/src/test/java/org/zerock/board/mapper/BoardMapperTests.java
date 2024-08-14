package org.zerock.board.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.persistence.DataSourceTest;

import com.webjjang.util.page.PageObject;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//Mapper 메서드 동작 테스트(단위 테스트)
//test에 사용되는 클래스 
@RunWith(SpringJUnit4ClassRunner.class)
//설정 파일 지정 -> 서버와 상관없음. : root-context.xml
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//log 객체 생성 -> lombok : log이름으로 처리
@Log4j
public class BoardMapperTests {
	
	// lombok의 setter를 이용해서 Spring의 Autowired를 이용한 자동 DI 적용
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		
		log.info("[일반 게시판 리스트(list() test ]------------");
		
		//필여한 데이터(파라메터로 넘겨지는 데이터)는 하드코딩한다.
		PageObject pageObject = new PageObject();
		log.info(mapper.list(pageObject));
		
	}
	@Test
	public void testGetGetTotalRow() {
		
		log.info("[일반 게시판 총 글수 (getTotalRow() test ]------------");
		
		//필여한 데이터(파라메터로 넘겨지는 데이터)는 하드코딩한다.
		PageObject pageObject = new PageObject();
		log.info(mapper.getTotalRow(pageObject));
		
	}
	@Test
	public void testGetIncrease() {
		
		log.info("[일반 게시판 조회수 처리(increase() test ]------------");
		
		//필여한 데이터(파라메터로 넘겨지는 데이터)는 하드코딩한다.
		Long no = 222L;
		log.info(mapper.inc(no));
		
	}
	@Test
	public void testGetView() {
		
		log.info("[일반 게시판 리스트(view() test ]------------");
		
		//필여한 데이터(파라메터로 넘겨지는 데이터)는 하드코딩한다.
		Long no = 222L;
		log.info(mapper.view(no));
		
	}
}
