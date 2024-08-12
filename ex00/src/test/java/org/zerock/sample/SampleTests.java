package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
// test에 사용되는 클래스
@RunWith(SpringJUnit4ClassRunner.class)
// 설정 파일 지정 -> 서버와 상관없음 : root-context.xml
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// log 객체 생성 - > lombok : log 이름으로 처리
@Log4j
public class SampleTests {
	
	// 레스토랑의 자동 생성과 DI 확인을 위해 객체 전달: 자동 DI
	@Setter(onMethod_ = {@Autowired}) // {}쓰는 이유는 {@Autowired , @Inject} 이런식으로 여러개를 쓸 수 있기 때문이다.
	private Restaurant restaurant;
	
	// 테스트할 메소드를 작성 -> 여러개 가능 : 한꺼번에 다 테스트한다.
	@Test
	public void testExist() {
		
		// not null 확인 
		assertNotNull(restaurant);
		
		// 출력해서 확인해 보기
		log.warn("ww-----------");
		log.info(restaurant);
		log.info("----------------------------");
		log.info(restaurant.getChef());
		
	}
}
