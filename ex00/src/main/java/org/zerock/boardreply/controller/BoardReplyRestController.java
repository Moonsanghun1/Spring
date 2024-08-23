package org.zerock.boardreply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.boardreply.service.BoardReplyService;
import org.zerock.boardreply.vo.BoardReplyVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

//자동생성
@RestController
@RequestMapping("/boardreply")
@Log4j
public class BoardReplyRestController {
	
	// 자동 DI 
//	@Setter(onMethod_ = @Autowired )
	@Autowired 
	@Qualifier("BoardReplyServiceImpl")
	private BoardReplyService service;
	
	// 1. list - get
	@GetMapping(value = "/list.do", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE
			
	})
	public ResponseEntity<Map<String, Object>> list(PageObject pageObject, Long no){
		log.info("list - page :" + pageObject.getPage() + ", no : " + no);
		// DB에서 데이터를 가져와서 넘겨준다.
		List<BoardReplyVO> list = service.list(pageObject, no);
		// PageObject도 념겨야 한다. 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		log.info("After - map : " +  map);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		// list와 pageObject의 데이터를 확인
		
	}
	
	// 2. write - post
	@PostMapping(value = "/write.do", 
			consumes = "application/json", //no, content
			produces = "text/plain; charset=UTF-8"
			) 
	// BoardReplyVO - 글번호(no), 내용(context) + id
	public ResponseEntity<String> write(@RequestBody BoardReplyVO vo, HttpSession session) {
		
		// 로그인이 되어있어야 사용할 수 있다. 
		vo.setId(getId(session));
		// 등록 처리
		service.write(vo);
		
		return new ResponseEntity<String>("댓글 등록이 되었습니다.", HttpStatus.OK);
	}
	// 3. update - post
	@PostMapping(value = "/update.do", 
			consumes = "application/json", //no, content
			produces = "text/plain; charset=UTF-8"
			) 
	public ResponseEntity<String> update(@RequestBody BoardReplyVO vo, HttpSession session) {
		
		// 로그인이 되어있어야 사용할 수 있다. 
		vo.setId(getId(session));
		
		Integer result = service.update(vo);
		
		if(result == 1)
			return new ResponseEntity<String>("댓글 수정이 되었습니다.", HttpStatus.OK);
		else	
			return new ResponseEntity<String>("댓글 수정에 실패했습니다.", HttpStatus.OK);
		
	}
	// 4. delete - get
	@GetMapping(value = "/delete.do", 
			produces = "text/plain; charset=UTF-8"
			) 
	public ResponseEntity<String> delete(BoardReplyVO vo, HttpSession session) {
		
		// 로그인이 되어있어야 사용할 수 있다. 
		vo.setId(getId(session));
		
		Integer result = service.delete(vo);
		
		if(result == 1)
			return new ResponseEntity<String>("댓글 수정이 되었습니다.", HttpStatus.OK);
		else	
			return new ResponseEntity<String>("댓글 수정에 실패했습니다.", HttpStatus.OK);
	}
	private String getId(HttpSession session) {
		// LoginVO vo = (LoginVO)sesstion.getAttribute("login");
		return "test";
	}
}
