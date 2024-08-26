package org.zerock.util.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zerock.member.vo.LoginVO;

import lombok.extern.log4j.Log4j;
@Log4j
public class AuthrityInterceptor extends HandlerInterceptorAdapter{
	
	// 권한 정보가 들어있는 map<uri, 등급>
	private Map<String, Integer> authMap = new HashMap<>();
	// 권한 정보를 등록하는 초기화 블록
	{	
		
		// 등급이 1이면 로그인 필요, 등급이 9면 로그인 & 관리자
		// 댓글
		authMap.put("/boardreply/write.do", 1);
		
		// 상품관리 - 페이지 권한
		authMap.put("/goods/writeForm.do", 9);
		authMap.put("/goods/write.do", 9);
		authMap.put("/goods/updateForm.do", 9);
		authMap.put("/goods/update.do", 9);
		authMap.put("/goods/delete.do", 9);
		authMap.put("/goods/changeImage.do", 9);
		
		// 회원관리
		authMap.put("/member/logout.do", 1);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		
		// 권한처리 진행
		log.info("권한 처리 Interceptor..............");
		
		// 로그인 여부 - 사용자 등급번호 (login -session). 페이지 권한(authMap)
		// 페이지 권한 가져오기
		String uri = request.getRequestURI();
		Integer pageGradeNo = authMap.get(uri);
		
		if (pageGradeNo != null) {
			HttpSession session = request.getSession();
			LoginVO vo = (LoginVO) session.getAttribute("login");
			// 로그인을 안한 경우
			if(vo == null) {
				
				// 권한 오류 JSP로 이동 시킨다.
				request.getRequestDispatcher("/WEB-INF/views/error/loginError.jsp").forward(request, response);
				
				return false;
				
			}
			// 페이지 권한 확인
			Integer userGradeNo = vo.getGradeNo();
			if (pageGradeNo > userGradeNo) {
				request.getRequestDispatcher("/WEB-INF/views/error/authError.jsp").forward(request, response);
			}
		}
		
		// 요청한 내용을 계속 진행 - 적합한 권한인 경우
		return super.preHandle(request, response, handler);

		 // end of preHandle
	}
	
}
