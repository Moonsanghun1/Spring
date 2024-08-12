package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	@RequestMapping("") // /sample/
	// return이 없으면(void)이면 uri 정보를 jsp 정보로 사용
	// return이 String이면 redirect: -> redirect 시킨다. 없으면 jsp로 forward 시킨다.
	public void basic() {
		log.info("basic.............");
	}
	// @RequestMapping은 uri 맵핑이 get, post 방식만 허용
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		
		log.info("basic get/post ............");
		
	}
	// get만 사용. value 속성 하나만 남으면 기본으로 데이터가 들어가는 속성이 된다. 생략 가능
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		
		log.info("basic get only get.........");
		
	}
	
	// get 방식 맵핑
	@GetMapping("/ex01")
	// property(VO = DTO)로 넘어오는 데이터 받기(setter 이름과 name이 같으면 자동으로 받는다.)
	public String ex01(SampleDTO dto) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex01() : dto=" + dto);
	return "ex01";
	}
	// get 방식 맵핑
	@GetMapping("/ex02")
	// parameter 병수로 받기 - 변수명과 name이 같아야 한다. age가 없으면 오류가 난다.
	public String ex02(@RequestParam("name")String name,@RequestParam(defaultValue = "0" ) int age) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex02() : name=" + name + "age= " + age);
		return "ex02";
	}
	// get 방식 맵핑
	@GetMapping("/ex02List")
	// parameter 병수로 받기 - 아이디 여러개를 받아서 처리 - List / 배열
	// List로 여러개의 데이터를 받을 떄는 @RequestParam을 꼭 써야한다.
	public String ex02List(@RequestParam ArrayList<String> ids, String[] names) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex02List() : ids=" + ids + ", names= " + Arrays.toString(names));
		for (String id : ids) {
			log.info("ex02List() : id=" + id);
		}
		return "ex02List";
	}
	// get 방식 맵핑
	@GetMapping("/ex03")
	// parameter 병수로 받기 - 아이디 여러개를 받아서 처리 - List / 배열
	// List로 여러개의 데이터를 받을 떄는 @RequestParam을 꼭 써야한다.
	public String ex03(TodoDTO dto) {
		// /WEB-INF/views/ + ex01 + .jsp
		log.info("ex03() : dto=" + dto);
		return "ex03";
	}
}
