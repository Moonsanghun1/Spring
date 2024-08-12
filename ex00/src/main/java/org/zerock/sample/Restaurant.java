package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.Setter;

@Data
@Component
// 자동 DI - private 변수가 많을 때 - 한꺼번에 생성자를 이용해서 데이터를 세팅할 수 있다.
//@AllArgsConstructor
public class Restaurant {

	// 자동 DI
	// lombok : @Setter(onMethod_ = @Autowired), Spring -
	@Autowired
	// JAVA : @Inject
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
