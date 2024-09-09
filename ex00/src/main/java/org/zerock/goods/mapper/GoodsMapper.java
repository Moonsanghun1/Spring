package org.zerock.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.goods.vo.ColorVO;
import org.zerock.goods.vo.GoodsImageVO;
import org.zerock.goods.vo.GoodsOptionVO;
import org.zerock.goods.vo.GoodsSearchVO;
import org.zerock.goods.vo.GoodsSizeColorVO;
import org.zerock.goods.vo.GoodsVO;
import org.zerock.goods.vo.SizeVO;

import com.webjjang.util.page.PageObject;

@Repository
public interface GoodsMapper {
	

	// 상품 리스트
	public List<GoodsVO> list(@Param("pageObject")PageObject pageObject, @Param("searchVO")GoodsSearchVO searchVO);
	
	public Long getTotalRow(@Param("searchVO")GoodsSearchVO searchVO);
	
	public Integer write(GoodsVO vo);
	public Integer writeImage(List<GoodsImageVO> goodsImageList);
	public Integer writeSizeColor(List<GoodsSizeColorVO> goodsSizeColorList);
	public Integer writeOption(List<GoodsOptionVO> goodsOptionList);
	public Integer writePrice(GoodsVO vo);

	public GoodsVO view(@Param("goods_no")Long good_no);
	public List<GoodsImageVO> viewImageList(Long goods_no);
	public List<GoodsOptionVO> optionList(Long goods_no);
	public List<GoodsSizeColorVO> sizeColorList(Long goods_no);
	
	public Integer inc(@Param("goods_no")Long good_no);

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
