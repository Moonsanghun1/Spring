package org.zerock.goods.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.goods.mapper.GoodsMapper;
import org.zerock.goods.vo.ColorVO;
import org.zerock.goods.vo.GoodsImageVO;
import org.zerock.goods.vo.GoodsOptionVO;
import org.zerock.goods.vo.GoodsSearchVO;
import org.zerock.goods.vo.GoodsSizeColorVO;
import org.zerock.goods.vo.GoodsVO;
import org.zerock.goods.vo.SizeVO;

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
	public List<GoodsVO> list(PageObject pageObject, GoodsSearchVO searchVO) {
		pageObject.setTotalRow(mapper.getTotalRow(searchVO));
		// 게시글 전체 개수 구하기
		return mapper.list(pageObject, searchVO);
	}
	
	// @Transactional - insert 2번이 성공을 해야 commit한다. 한개라도 오류가 나면 rollback.
	// 상품, 가격, 이미지, 사이즈컬러, 옵션 -> 등록하다가 하나라도 오류가 나면 다 rollback

	@Override
	public Integer write(GoodsVO vo, List<GoodsImageVO> goodsImageList,
			List<GoodsSizeColorVO> goodsSizeColorList,
			List<GoodsOptionVO> goodsOptionList) {
		Integer result = null; // 글번호를 시퀀스에서 새로운 번호 사용
		// 상품 상세 정보 - vo : 필수 - 처리가 끝나면 goods_no 세팅되서 넘어온다.
		mapper.write(vo);
		// 추가 이미지 goodsImageList. null이 아닌 경우에만 DB에 추가 
		if(goodsImageList != null && goodsImageList.size() > 0) {
			for(GoodsImageVO imageVO : goodsImageList)
				imageVO.setGoods_no(vo.getGoods_no());
			mapper.writeImage(goodsImageList);
		}
		// 사이즈와 색상 - goodsSizeColorList. null이 아닌 경우에만 DB에 추가 
		if(goodsSizeColorList != null && goodsSizeColorList.size() > 0) {
			for(GoodsSizeColorVO sizeColorVO : goodsSizeColorList)
				sizeColorVO.setGoods_no(vo.getGoods_no());
			mapper.writeSizeColor(goodsSizeColorList);
		}
		// 추가 이미지 goodsOptionList. null이 아닌 경우에만 DB에 추가 
		if (goodsOptionList != null && goodsOptionList.size() > 0) {
		    for (GoodsOptionVO optionVO : goodsOptionList) {
		        optionVO.setGoods_no(vo.getGoods_no());  // 각 옵션에 goods_no를 설정
		    }
		    mapper.writeOption(goodsOptionList);  // goodsOptionList가 null이 아니고 비어 있지 않은 경우에만 매퍼 호출
		}
		// 가격 등록
		mapper.writePrice(vo);
		return result; 
	}
	@Override
	public GoodsVO view(Long goods_no, int inc) {
		if(inc==1) mapper.inc(goods_no);
		return mapper.view(goods_no);
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

	// 상품 사이즈 가져오기
	@Override
	public List<SizeVO> getSize(Integer cate_code1) {
		return mapper.getSize(cate_code1);
	}

	// 상품 색상 가져오기
	@Override
	public List<ColorVO> getColor(Integer cate_code1) {
		return mapper.getColor(cate_code1);
	}

	@Override
	public List<GoodsImageVO> viewImageList(Long goods_no) {
		log.info("viewImageList() 실행");
		return mapper.viewImageList(goods_no);
	}
	@Override
	public List<GoodsSizeColorVO> sizeColorList(Long goods_no) {
		log.info("viewImageList() 실행");
		return mapper.sizeColorList(goods_no);
	}
	@Override
	public List<GoodsOptionVO> optionList(Long goods_no) {
		log.info("viewImageList() 실행");
		return mapper.optionList(goods_no);
	}
}
