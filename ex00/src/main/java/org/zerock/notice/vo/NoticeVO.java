package org.zerock.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeVO {
	private Long no;
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate; // sql - java.sql.Date : casting - spring에서는 자동 캐스팅
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
}
