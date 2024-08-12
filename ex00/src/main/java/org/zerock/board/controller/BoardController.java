package org.zerock.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.board.service.BoardService;
import org.zerock.board.vo.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	// 자동 DI
	@Setter(onMethod_ = @Autowired)
	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Qualifier("boardService")
	private BoardService service;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		log.info("list.do");
		//request.setAttribute("list", service.list());
		model.addAttribute("list", service.list());
		return "board/list";
		
		//ModelAndView
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", service.list());
//		mav.setViewName("board/list");
//		return mav;
	}
	@GetMapping("/view.do")
	public String view(Model model, Long no, int inc) {
		log.info("BoardController.view()");
		
		model.addAttribute("vo", service.view(no,inc));
		return "board/view";
	}
	@GetMapping("/writeForm.do")
	public String writeForm() {
		log.info("BoardController.writeForm()");
		return "board/writeForm";
	}
	@PostMapping("/write.do")
	public String write(BoardVO vo) {
		log.info("BoardController.write()");
		log.info(vo);
		service.write(vo);
		return "redirect:list.do";
	}
	@GetMapping("/updateForm.do")
	public String updateForm() {
		log.info("BoardController.updateForm()");
		return "board/updateForm";
	}
	@PostMapping("/update.do")
	public String update() {
		log.info("BoardController.update()");
		return "redirect:list.do";
	}
	@PostMapping("/delete.do")
	public String delete() {
		log.info("BoardController.delete()");
		return "redirect:list.do";
	}
	
}
