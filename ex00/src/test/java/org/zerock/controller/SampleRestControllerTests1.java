package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class SampleRestControllerTests1 {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	// Spring의 WebMVC 테스팅을 위한 가짜 MVC 객체
	private MockMvc mockMvc;
	
	@Before // Test 전에 실행되는 메소드
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	@Test
	public void testConvert() throws Exception{
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		String jsonStr = new Gson().toJson(ticket);
		
		log.info(jsonStr);
		
		mockMvc.perform(post("/sampleRest/sample").contentType(MediaType.APPLICATION_JSON).content(jsonStr)).andExpect(status().is(200));
	}
}
