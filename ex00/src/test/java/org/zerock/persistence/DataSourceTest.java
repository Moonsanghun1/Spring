package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//test에 사용되는 클래스 
@RunWith(SpringJUnit4ClassRunner.class)
// 설정 파일 지정 -> 서버와 상관없음. : root-context.xml
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//log 객체 생성 -> lombok : log이름으로 처리
@Log4j
public class DataSourceTest {
	//자동 DI - root-context.xml에 bean 태그로 설정
	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSource;
	//자동 DI - root-context.xml에 bean 태그로 설정
	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	@Test
	public void testConnection() {
		log.info("-------------[CP 테스트]-------------");
		//try(resource) -> try가 종료되면 resource가 자동 닫기가 된다.
		try(Connection con = dataSource.getConnection()) {
			log.info(con);
			
		} catch (Exception e) {
			// TODO: handle exception
			fail(e.getMessage());
		}
	}
	@Test
	public void testMybatis() {
		log.info("-------------[Mybatis 테스트]-------------");
		//try(resource) -> try가 종료되면 resource가 자동 닫기가 된다.
		try(SqlSession session= sqlSessionFactory.openSession();
				Connection con = session.getConnection()) {
			log.info(session);
			log.info(con);
			
		} catch (SQLException e) {
			// TODO: handle exception
			fail(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	
}
