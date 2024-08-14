package org.zerock.board.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.service.BoardService;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	

	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService service;
	
	@GetMapping("/list.do")
	public String list(Model model, HttpServletRequest request) throws Exception {
		log.info("list.do");
		
		PageObject pageObject = PageObject.getInstance(request);
		log.info(pageObject);
		
		//request.setAttribute("list", service.list());
		model.addAttribute("list", service.list(pageObject));
		log.info(pageObject);
		model.addAttribute("pageObject", pageObject);
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
	public String write(BoardVO vo, RedirectAttributes rttr) {
		log.info("BoardController.write()");
		log.info(vo);
		service.write(vo);
		// 처리 결과에 대한 메시지 처리
		rttr.addFlashAttribute("msg", "일반 게시판 글등록이 되었습니다.");
		return "redirect:list.do";
	}
	@GetMapping("/updateForm.do")
	public String updateForm(Model model, Long no, int inc) {
		
		log.info("BoardController.updateForm()");
		
		model.addAttribute("vo", service.view(no,inc));
		
		return "board/updateForm";
	}
	@PostMapping("/update.do")
	public String update(BoardVO vo, RedirectAttributes rttr) {
		log.info("BoardController.update()");
		if(service.update(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 수정이 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글수정이 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		Long no = vo.getNo();
		return "redirect:view.do?no="+no+"&inc=0";
	}
	@PostMapping("/delete.do")
	public String delete(BoardVO vo, RedirectAttributes rttr) {
		log.info("BoardController.delete()");
		if(service.delete(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		
		return "redirect:list.do";
	}
	
}
