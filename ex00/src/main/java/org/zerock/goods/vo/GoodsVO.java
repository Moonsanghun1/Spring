package org.zerock.goods.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class GoodsVO {
	private Long goods_no;
	private Integer cate_code1;
	private Integer cate_code2;
	private String goods_name;
	private String content; // 보기에 나타날 상세 설명 텍스트
	private String detail_image_name;// 보기에 나타날 상세 설명 이미지
	private Date product_date;
	private String company;
	private String image_name; // 리스트에 나타날 대표이미지 
	private Long hit;
	private Integer price; // 현재 판매 가격
	private Integer discount; // 현재 가격에 대한 할인가
	private Integer discount_rate; // 현재 가격에 대한 할인율
	private Integer delivery_charge; // 배송료
	private Integer saved_rate; // 적립율
	private Date sale_startDate; // 할인 적용일
	private Date sale_endDate; // 할인 만료일
	
}
