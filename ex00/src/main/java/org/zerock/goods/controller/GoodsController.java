package org.zerock.goods.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.category.service.CategoryService;
import org.zerock.goods.service.GoodsService;
import org.zerock.goods.vo.GoodsImageVO;
import org.zerock.goods.vo.GoodsOptionVO;
import org.zerock.goods.vo.GoodsSizeColorVO;
import org.zerock.goods.vo.GoodsVO;

import com.webjjang.util.file.FileUtil;
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
	
	String path = "/upload/goods";
	
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
	public String write(GoodsVO vo, 
		// 대표 이미지
		MultipartFile imageFile, 
		// 상세 설명 이미지
		MultipartFile detailImageFile, 	
		// 추가 이미지들
		ArrayList<MultipartFile> imageFiles,
		// 옵션들 받기 - 사이즈, 컬러, 옵션 : 데이터 1개의 객체가 1개의 단위인 경우 @RequestParam(required = false) 붙여야 한다. 배열인 경우 오류 안남
		@RequestParam(name="size_nos", required = false) ArrayList<Long> size_nos,
		@RequestParam(name="color_nos", required = false) ArrayList<Long> color_nos,
		@RequestParam(name="option_names", required = false) ArrayList<String> option_names,
		
		HttpServletRequest request,
		RedirectAttributes rttr) throws Exception {
		log.info("GoodsController.write()==========================");
		log.info(vo);
		log.info("대표이미지: " + imageFile.getOriginalFilename() );
		log.info("상세 설명 이미지: " + detailImageFile.getOriginalFilename());
		log.info("첨부 이미지들: ");
		for(MultipartFile file : imageFiles)
			log.info(file.getOriginalFilename());
		log.info("사이즈: "+ size_nos);
		log.info("색상: "+ color_nos);
		log.info("옵션: "+ option_names);
		String strPerPageNum = request.getParameter("perPageNum");
		// 이미지 올리가와 DB에 저장할 데이터 수집
		log.info("<<<--------이미지 처리-------->>");
		// 대표 이미지 처리
		vo.setImage_name(FileUtil.upload(path, imageFile, request));
		// 상품 상세 이미지
		String fileName = detailImageFile.getOriginalFilename();
		if(fileName != null && !fileName.equals(""))
			vo.setDetail_image_name(FileUtil.upload(path, detailImageFile, request));
		List<GoodsImageVO> goodsImageList = null;
		// 첨부 이미지 - GoodsImageVO
		if (imageFiles != null && imageFiles.size() > 0) {
			for(MultipartFile file : imageFiles) {
				if (goodsImageList == null) goodsImageList = new ArrayList<>();
				fileName = file.getOriginalFilename();
				if(fileName != null && !fileName.equals("")) {
					GoodsImageVO ImageVO = new GoodsImageVO();
					// 파일은 서버에 올리고 DB에 저장할 정보를 VO에 저장한다.
					ImageVO.setImage_name(FileUtil.upload(path, file, request));
					goodsImageList.add(ImageVO);
				}
			}
		}
		log.info(vo);	
		// 첨부 이미지 목록 확인
		log.info(goodsImageList);	
		// 사이즈와 컬러 - 데이터 개수 : 사이즈 * 컬러 - GoodsSizeColorVO
		List<GoodsSizeColorVO> goodsSizeColorList =null;
		// 사이즈가 있는 경우 처리(옵션이 없다.)
		if(size_nos != null && size_nos.size() > 0) {
			for(Long sizeNo : size_nos) {
				if(goodsSizeColorList == null) goodsSizeColorList = new ArrayList<>();
				if(color_nos != null && color_nos.size() > 0) {
					for(Long colorNo : color_nos) {
						GoodsSizeColorVO sizeColorVO = new GoodsSizeColorVO();
						sizeColorVO.setSize_no(sizeNo);
						sizeColorVO.setColor_no(colorNo);
						goodsSizeColorList.add(sizeColorVO);
					}
				}else { // 컬러가 없는 경우
					GoodsSizeColorVO sizeColorVO = new GoodsSizeColorVO();
					sizeColorVO.setSize_no(sizeNo);
					goodsSizeColorList.add(sizeColorVO);
				}
			}
		}
		log.info("goodsSizeColorList: "+goodsSizeColorList);	
		
		// 옵션 - GoodsOptionVO
		List<GoodsOptionVO> goodsOptionList = null;
		// 옵션이 있는 경우 처리
		if (option_names != null && option_names.size() > 0) {
			for(String option_name : option_names) {
				if(goodsOptionList == null) goodsOptionList = new ArrayList<>();
				// 비어 있지 않으면 추가한다. - if문 추가 필요
				if(option_names != null && option_names.equals("")) {
					GoodsOptionVO optionVO = new GoodsOptionVO();
					optionVO.setOption_name(option_name);
					goodsOptionList.add(optionVO);
				}
			}
		}
		log.info("goodsOptionList: "+ goodsOptionList);
		// service.write()에 넘길 데이터 - vo, goodsImageList, goodsSizeColorList, goodsOptionList
		service.write(vo, goodsImageList, goodsSizeColorList, goodsOptionList);
		// 처리 결과에 대한 메시지 처리
		log.info("상품등록이 되었습니다. 상품 번호: "+vo.getGoods_no());
		rttr.addFlashAttribute("msg", "상품 등록이 되었습니다.");
		return "redirect:list.do?perPageNum="+strPerPageNum;
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
