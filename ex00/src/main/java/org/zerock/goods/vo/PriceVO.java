package org.zerock.goods.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class PriceVO {
	private Long price_no;
	private Long goods_no;
	private Integer price; // 현재 판매 가격
	private Integer discount; // 현재 가격에 대한 할인가
	private Integer discount_rate; // 현재 가격에 대한 할인율
	private Integer delivery_charge; // 배송료
	private Integer saved_rate; // 적립율
	private Date sale_startDate; // 할인 적용일
	private Date sale_endDate; // 할인 만료일
}
