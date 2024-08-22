package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // AOP 프로그램 지정
@Log4j
// 자동 생성 - @Controller, RestController, Service, Repository, Component, ~Advice
@Component 
public class LogAdvice {
	
	@Around("execution(* org.zerock.*.service.*ServiceImpl.*(..))")
	// ProceedingJoinPoint - 실행해야 할 객체(~ServiceImpl) + parameter
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable{
		
		// 결과를 저장하는 변수
		Object result =null;
		// 시작 시간 저장
		long start = System.currentTimeMillis();
		
		log.info("*******************[AOP 실행 전 로그 출력]*******************");
		log.info(" 시작 시간: " + start);
		// 실행되는 객체의 이름 출력
		log.info(" 실행 객체, 메소드: "+pjp.getSignature());
		// 전달되는 파라미터 데이터 출력 
		log.info(" 전달 데이터: " + Arrays.toString(pjp.getArgs()));
		// 실행하는 부분 - 다른 AOP가 있으면 그쪽으로 가서 진행 처리해세요.
		result = pjp.proceed();
		// 시작 시간 저장
		long end = System.currentTimeMillis();
		log.info("*******************[AOP 실행 후 로그 출력]*******************");
		log.info(" 소요 시간: " + (end-start));
		// 실행한 결과 데이터 출력 
		log.info(" 결과 데이터: " + result);
		return result;
		
	}
	
}
