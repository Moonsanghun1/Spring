package org.zerock.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.notice.service.NoticeService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/notice")
@Controller
public class NoticeController {
		
	@Autowired
	@Qualifier("noticeServiceImpl")
	private NoticeService service;
	
	@GetMapping("/list.do")
	public String list(Model model) throws Exception{
		
		model.addAttribute("list", service.list());
		
		return "notice/list";
	}
}
