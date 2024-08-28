package org.zerock.category.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.category.mapper.CategoryMapper;
import org.zerock.category.vo.CategoryVO;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private CategoryMapper mapper;
	
	@Override
	public List<CategoryVO> list(Integer cate_code1) {
		log.info("list() 실행");
		// 게시글 전체 개수 구하기
		return mapper.list(cate_code1);
	}
	// @Transactional - isert 2번이 성공을 해야 commit한다. 한개라도 오류가 나면 rollback.
	@Transactional 
	@Override
	public Integer write(CategoryVO vo) {
		
		if(vo.getCate_code1()==0) return mapper.writeMaj(vo);
		
		return mapper.writeMid(vo);
		
	}

	@Override
	public Integer update(CategoryVO vo) {
		log.info("update() 실행");
		return mapper.update(vo);
	}
	@Override
	public Integer delete(CategoryVO vo) {
		log.info("delete() 실행");
		return mapper.delete(vo);
	}
}
