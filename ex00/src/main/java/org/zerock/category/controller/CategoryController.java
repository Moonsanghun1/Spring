package org.zerock.category.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.category.service.CategoryService;
import org.zerock.category.vo.CategoryVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/category")
@Log4j
public class CategoryController {
	

	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService service;
	
	@GetMapping("/list.do")
	public String list(Model model, @RequestParam(defaultValue = "0") Integer cate_code1) throws Exception {

		// 대분류 가져오기. 
		List<CategoryVO> majList = service.list(0);
		// cate_code1이 없으면 cate_code1 중에서 제일 작은 것을 가져와서 처리
		if(cate_code1 == 0 && (majList != null && majList.size() != 0)) {
			cate_code1 = majList.get(0).getCate_code1();
		}
		// 대분류 가져오기. 
		List<CategoryVO> midList = service.list(cate_code1);
		//request.setAttribute("list", service.list());
		model.addAttribute("majList", majList);
		model.addAttribute("midList", midList);

		return "category/list";
		

	}

	// 카테고리 글 등록 폼
	@PostMapping("/write.do")
	public String write(CategoryVO vo, RedirectAttributes rttr) {
		log.info("CategoryController.write()");
		log.info(vo);
		service.write(vo);
		// 처리 결과에 대한 메시지 처리
		rttr.addFlashAttribute("msg", "일반 게시판 글등록이 되었습니다.");
		return "redirect:list.do";
	}

	// 카테고리 글 수정 폼
	@PostMapping("/update.do")
	public String update(CategoryVO vo, RedirectAttributes rttr) {
		log.info("CategoryController.update()");
		if(service.update(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 수정이 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글수정이 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		
		return "redirect:list.do";
	}
	@PostMapping("/delete.do")
	public String delete(CategoryVO vo, RedirectAttributes rttr) {
		log.info("CategoryController.delete()");
		if(service.delete(vo)==1)
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "일반 게시판 글 삭제가 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		
		return "redirect:list.do";
	}
	
}
