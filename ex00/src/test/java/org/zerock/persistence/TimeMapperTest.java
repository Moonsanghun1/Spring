package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTest {
	//자동 DI - root-context.xml에 bean 태그로 설정
	@Setter(onMethod_ = {@Autowired})
	private TimeMapper timeMapper;

	@Test
	public void testGetTime() {
		log.info("----------[Mapper 어노테이션 테스트]---------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
		log.info(timeMapper.getClass());
	}
	@Test
	public void testGetTime2() {
		log.info("----------[Mapper ddd 테스트]---------");
		log.info("getTime2");
		log.info(timeMapper.getTime2());
	}

	
	
}
