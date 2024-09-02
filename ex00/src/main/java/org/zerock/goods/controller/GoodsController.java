package org.zerock.goods.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.category.service.CategoryService;
import org.zerock.goods.service.GoodsService;
import org.zerock.goods.vo.GoodsVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/goods")
@Log4j
public class GoodsController {
	

	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Autowired
	@Qualifier("goodsServiceImpl")
	private GoodsService service;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	@GetMapping("/list.do")
	// 검색을 위한 데이터를 따로 받아야한다.
	public String list(Model model, HttpServletRequest request) throws Exception {
		log.info("list.do");
		
		PageObject pageObject = PageObject.getInstance(request);	
		
		// 한 페이지당 보여주는 데이터의 개수가 없으면 기본은 8로 정한다.
		String strPerPageNum = request.getParameter("perPageNum");
		if(strPerPageNum == null || strPerPageNum.equals("")) pageObject.setPerPageNum(8);
			
		
		//request.setAttribute("list", service.list());
		model.addAttribute("list", service.list(pageObject));
		log.info(pageObject);
		model.addAttribute("pageObject", pageObject);
		// 검색에 대한 정보도 넘겨야 한다.
		return "goods/list";
	
	}
	@GetMapping("/view.do")
	public String view(Model model, Long no, int inc) {
		log.info("GoodsController.view()");
		model.addAttribute("vo", service.view(no,inc));
		return "goods/view";
	}
	@GetMapping("/writeForm.do")
	public String writeForm(Model model) {
		// 대분류를 가져와서 JSP로 넘기기
		model.addAttribute("majList", categoryService.list(0));
		return "goods/writeForm";
	}

	@PostMapping("/write.do")
	public String write(GoodsVO vo, RedirectAttributes rttr) {
		log.info("GoodsController.write()");
		log.info(vo);
		service.write(vo);
		// 처리 결과에 대한 메시지 처리
		rttr.addFlashAttribute("msg", "일반 게시판 글등록이 되었습니다.");
		return "redirect:list.do";
	}
	@GetMapping("/updateForm.do")
	public String updateForm(Model model, Long no, int inc) {
		
		log.info("GoodsController.updateForm()");
		
		model.addAttribute("vo", service.view(no,inc));
		
		return "goods/updateForm";
	}
	@PostMapping("/update.do")
	public String update(GoodsVO vo, RedirectAttributes rttr) {
		log.info("GoodsController.update()");
		if(service.update(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 수정이 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글수정이 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		
		return "redirect:view.do?no=";
	}
	@PostMapping("/delete.do")
	public String delete(GoodsVO vo, RedirectAttributes rttr) {
		log.info("GoodsController.delete()");
		if(service.delete(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		
		return "redirect:list.do";
	}
	
}
