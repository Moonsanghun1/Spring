package org.zerock.category.service;

import java.util.List;

import org.zerock.category.vo.CategoryVO;


public interface CategoryService {
	
	// 카테고리 리스트
	public List<CategoryVO> list(Integer cate_code1);
	
	// 카테고리 대분류 등록 - cate_code1이 있으면 중분류 등록, 없으면 대분류 등록
	public Integer write(CategoryVO vo);
	
	
	
	public Integer update(CategoryVO vo);
	
	public Integer delete(CategoryVO vo);
}
