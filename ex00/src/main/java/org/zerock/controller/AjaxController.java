package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.category.service.CategoryService;
import org.zerock.goods.service.GoodsService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Autowired
	@Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	@GetMapping("/getMidList.do")
	public String getMidList(Model model, Integer cate_code1) {
		// 대분류를 가져와서 JSP로 넘기기
		model.addAttribute("midList", categoryService.list(cate_code1));
		// midList.jsp에 select tag 작성 
		return "goods/midList";
	}
	// 사이즈 가져오기
	@GetMapping("/getSize.do")
	public String getSize(Model model, Integer cate_code1) {
		// 대분류를 가져와서 JSP로 넘기기
		model.addAttribute("sizeList", goodsService.getSize(cate_code1));
		// midList.jsp에 select tag 작성 
		return "goods/sizeList";
	}
	
	// 색상 가져오기
	@GetMapping("/getColor.do")
	public String getColor(Model model, Integer cate_code1) {
		// 대분류를 가져와서 JSP로 넘기기
		model.addAttribute("colorList", goodsService.getColor(cate_code1));
		// midList.jsp에 select tag 작성 
		return "goods/colorList";
	}

	
}
