package org.zerock.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MainController {
	@GetMapping(value = {"/", "/main.do"})
	public String goMain() {
		log.info("redirect main");
		return "redirect:/main/main.do";
	}
	
	@GetMapping(value = {"/main/main.do"})
	public String Main() {
		log.info("redirect main");
		return "main/main";
	}
	
}
