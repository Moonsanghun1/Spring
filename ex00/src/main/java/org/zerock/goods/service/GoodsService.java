package org.zerock.goods.service;

import java.util.List;

import org.zerock.goods.vo.ColorVO;
import org.zerock.goods.vo.GoodsImageVO;
import org.zerock.goods.vo.GoodsOptionVO;
import org.zerock.goods.vo.GoodsSearchVO;
import org.zerock.goods.vo.GoodsSizeColorVO;
import org.zerock.goods.vo.GoodsVO;
import org.zerock.goods.vo.SizeVO;

import com.webjjang.util.page.PageObject;


public interface GoodsService {
	
	public List<GoodsVO> list(PageObject pageObject, GoodsSearchVO searchVO);
	
	public Integer write(GoodsVO vo, List<GoodsImageVO> goodsImageList,
			List<GoodsSizeColorVO> goodsSizeColorList,
			List<GoodsOptionVO> goodsOptionList);
	
	public GoodsVO view(Long goods_no, int inc);
	public List<GoodsImageVO> viewImageList(Long goods_no);
	public List<GoodsSizeColorVO> sizeColorList(Long goods_no);
	public List<GoodsOptionVO> optionList(Long goods_no);
	
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
	
	// 상품 사이즈 가져오기
	public List<SizeVO> getSize(Integer cate_code1);
	// 상품 색상 가져오기
	public List<ColorVO> getColor(Integer cate_code1);

	

}
