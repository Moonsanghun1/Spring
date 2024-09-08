package org.zerock.stock.vo;

import lombok.Data;

@Data
public class StockVO {
	
	// 종목 코드
	private Integer company_id;
	// 조회할 시작날짜
	private String startDate;
	// 조회할 종료날짜
	private String endDate;
	// 기간 분류 코드 (D:일봉, W:주봉, M:월봉, Y:년봉)
	private String period_div_code;
	
}
