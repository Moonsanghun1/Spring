package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 생성자를 이용해서 파라미터로 값을 전달해서 저장시킨다.
@AllArgsConstructor
// 기본 생성자 생성
@NoArgsConstructor
public class SampleDTO {

	private String name;
	private int age;
	
}
