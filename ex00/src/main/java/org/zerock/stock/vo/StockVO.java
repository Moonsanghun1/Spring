package org.zerock.stock.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class StockVO {
	private Long no;
	private String title;
	private String content;
	private Date writeDate; // sql - java.sql.Date : casting - spring에서는 자동 캐스팅
	private Date endDate;
	private Date startDate;
	private Date updateDate;
}
