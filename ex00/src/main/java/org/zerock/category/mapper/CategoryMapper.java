package org.zerock.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.category.vo.CategoryVO;

@Repository
public interface CategoryMapper {
	
	// 카테고리 리스트
	public List<CategoryVO> list(@Param("cate_code1") Integer cate_code1);

	// 대분류 등록
	public Integer writeMaj(CategoryVO vo);
	
	// 중분류 등록
	public Integer writeMid(CategoryVO vo);

	public Integer update(CategoryVO vo);
	
	public Integer delete(CategoryVO vo);


}
