package org.zerock.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.goods.vo.ColorVO;
import org.zerock.goods.vo.GoodsVO;
import org.zerock.goods.vo.SizeVO;

import com.webjjang.util.page.PageObject;

@Repository
public interface GoodsMapper {
	
	// 상품 리스트
	public List<GoodsVO> list(PageObject pageObject);
	
	public Long getTotalRow(PageObject pageObject);
	
	public Integer write(GoodsVO vo);

	public GoodsVO view(Long no);
	
	public Integer inc(Long no);

	public Integer update(GoodsVO vo);
	
	public Integer delete(GoodsVO vo);
	
	// 상품 사이즈 가져오기
	public List<SizeVO> getSize(@Param("cate_code1") Integer cate_code1);
	// 상품 색상 가져오기
	public List<ColorVO> getColor(@Param("cate_code1") Integer cate_code1);
	
	// 상품 이미지 추가
	// 상품 이미지 변경
	// 상품 이미지 제거
	
	// 상품 사이즈 컬러 추가
	// 상품 사이즈 컬러 변경
	// 상품 사이즈 컬러 제거
	
	// 상품 현재 가격 변경 + 기간 변경
	// 상품 예정 가격 추가
}
