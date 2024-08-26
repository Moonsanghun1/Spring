package org.zerock.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.member.service.MemberService;
import org.zerock.member.vo.LoginVO;
import org.zerock.member.vo.MemberVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {
	

	// Type이 같으면 식별할 수 있는 문자열 지정 - id를 지정
	@Autowired
	@Qualifier("memberServiceImpl")
	private MemberService service;
	
	@GetMapping("/list.do")
	public String list(Model model, HttpServletRequest request) throws Exception {
		log.info("list.do");
		
		PageObject pageObject = PageObject.getInstance(request);
		log.info(pageObject);
		
		//request.setAttribute("list", service.list());
		model.addAttribute("list", service.list(pageObject));
		log.info(pageObject);
		model.addAttribute("pageObject", pageObject);
		return "Member/list";
		
		//ModelAndView
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", service.list());
//		mav.setViewName("member/list");
//		return mav;
	}
	@GetMapping("/view.do")
	public String view(Model model, Long no, int inc) {
		log.info("MemberController.view()");
		model.addAttribute("vo", service.view(no,inc));
		return "member/view";
	}
	@GetMapping("/writeForm.do")
	public String writeForm() {
		log.info("MemberController.writeForm()");
		return "member/writeForm";
	}
	@PostMapping("/write.do")
	public String write(MemberVO vo, RedirectAttributes rttr) {
		log.info("MemberController.write()");
		log.info(vo);
		service.write(vo);
		// 처리 결과에 대한 메시지 처리
		rttr.addFlashAttribute("msg", "멤버 글등록이 되었습니다.");
		return "redirect:list.do";
	}
	@GetMapping("/updateForm.do")
	public String updateForm(Model model, Long no, int inc) {
		
		log.info("MemberController.updateForm()");
		
		model.addAttribute("vo", service.view(no,inc));
		
		return "member/updateForm";
	}
	@PostMapping("/update.do")
	public String update(MemberVO vo, RedirectAttributes rttr) {
		log.info("MemberController.update()");
		if(service.update(vo)==1)
			rttr.addFlashAttribute("msg", "멤버 글 수정이 되었습니다.");
		else	{
			rttr.addFlashAttribute("msg", "멤버 글수정이 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");}
		String id = vo.getId();
		return "redirect:view.do?no="+id+"&inc=0";
	}
	@PostMapping("/delete.do")
	public String delete(MemberVO vo, RedirectAttributes rttr) {
		log.info("MemberController.delete()");
		if(service.delete(vo)==1) {
			rttr.addFlashAttribute("msg", "멤버 글 삭제가 되었습니다.");
			return "redirect:list.do";
		}
		else {
			rttr.addFlashAttribute("msg", "멤버 글 삭제가 되지 않았습니다. 글번호나 비밀번호가 맞지 않습니다. 다시 확인하고 시도해 주세요.");
			return "redirect:view.do?"+vo.getId() + "&inc=0";
			
		}
		
	}

	@GetMapping("/loginForm.do")
	public String loginForm() {
		log.info("MemberController.writeForm()");
		return "member/loginForm";
	}
	
	@PostMapping("/login.do")
	public String login(LoginVO vo, HttpSession session, RedirectAttributes rttr) {
		log.info("MemberController.login()");
		
		LoginVO loginVO = service.login(vo);
		
		if(loginVO == null) {
			rttr.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다");
			return "redirect:/member/loginForm.do";

		}
		
		session.setAttribute("login", loginVO);
		rttr.addFlashAttribute("msg", loginVO.getName()+"님은"+loginVO.getGradeName()+"(으)로 로그인 되었습니다");
		
		return "redirect:/main/main.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(LoginVO vo, HttpSession session, RedirectAttributes rttr) {
		log.info("MemberController.logout()");
		
		session.removeAttribute("login");
		
		rttr.addFlashAttribute("msg",
				"로그아웃 되었습니다. 불편한 사항을 질문 답변 게시판을 이용해 주세요.");
		
		return "redirect:/";
	}
	
}
