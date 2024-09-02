package org.zerock.goods.service;

import java.util.List;

import org.zerock.category.vo.CategoryVO;
import org.zerock.goods.vo.GoodsVO;

import com.webjjang.util.page.PageObject;


public interface GoodsService {
	
	public List<GoodsVO> list(PageObject pageObject);
	
	public Integer write(GoodsVO vo);
	
	public GoodsVO view(Long no, int inc);
	
	public Integer update(GoodsVO vo);
	
	public Integer delete(GoodsVO vo);

	// 상품 이미지 추가
	// 상품 이미지 변경
	// 상품 이미지 제거
	
	// 상품 사이즈 컬러 추가
	// 상품 사이즈 컬러 변경
	// 상품 사이즈 컬러 제거
	
	// 상품 현재 가격 변경 + 기간 변경
	// 상품 예정 가격 추가
	
}
