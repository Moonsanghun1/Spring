package org.zerock.goods.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.category.vo.CategoryVO;
import org.zerock.goods.mapper.GoodsMapper;
import org.zerock.goods.vo.GoodsVO;

import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j;

//자동생성을 위한 어노테이션 : 
//- @Controller - uri : HTML , @Service - data 처리 , @Repository - 데이터 저장 ,
//@Component - 구성체, @RestController - uri : data : ajax , @~Advice(예외 처리)
@Service
@Log4j
@Qualifier("goodsServiceImpl")
public class GoodsServiceImpl implements GoodsService{
	
	// 자동 DI 적용 @Setter, @Autowired, @inject
	@Inject
	private GoodsMapper mapper;
	
	@Override
	public List<GoodsVO> list(PageObject pageObject) {
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 게시글 전체 개수 구하기
		return mapper.list(pageObject);
	}
	
	// @Transactional - isert 2번이 성공을 해야 commit한다. 한개라도 오류가 나면 rollback.
	// 상품, 가격, 이미지, 사이즈컬러, 옵션 -> 등록하다가 하나라도 오류가 나면 다 rollback
	@Transactional 
	@Override
	public Integer write(GoodsVO vo) {
		Integer result = mapper.write(vo); // 글번호를 시퀀스에서 새로운 번호 사용
		//vo.setNo(10000L);
		//mapper.writeTx(vo); // 위에서 사용한 글번호 재사용 - PK 예외 발생
		return result; 
	}
	@Override
	@Transactional
	public GoodsVO view(Long no, int inc) {
		if(inc==1) mapper.inc(no);
		return mapper.view(no);
	}
	// 상품 글 수정 ㅌ
	@Override
	public Integer update(GoodsVO vo) {
		return mapper.update(vo);
	}
	// 상품 글 삭제 
	@Override
	public Integer delete(GoodsVO vo) {
		return mapper.delete(vo);
	}

}
