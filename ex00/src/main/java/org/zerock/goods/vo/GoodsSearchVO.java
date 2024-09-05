package org.zerock.goods.vo;

import lombok.Data;

@Data
public class GoodsSearchVO {
	
	private Integer cate_code1; // 대분류 검색 - 상품 등록
	private Integer cate_code2; // 중분류 검색 - 상품 등록
	private String goods_name; // 상품명에 포함되어 있는 문자 검색
	private Long min; // 최소 가격 검색
	private Long max; // 최대 가격 검색
}
