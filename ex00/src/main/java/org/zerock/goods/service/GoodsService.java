package org.zerock.goods.service;

import java.util.List;

import org.zerock.goods.vo.GoodsVO;

import com.webjjang.util.page.PageObject;


public interface GoodsService {
	
	public List<GoodsVO> list(PageObject pageObject);
	
	public Integer write(GoodsVO vo);
	
	public GoodsVO view(Long no, int inc);
	
	public Integer update(GoodsVO vo);
	
	public Integer delete(GoodsVO vo);
	
}
